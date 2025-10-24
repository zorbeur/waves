# WaveShop API

API Spring Boot pour la plateforme e-commerce WaveShop. Fournit l’authentification JWT, la gestion des vagues de vente, produits, promotions, commandes, livraisons, et envoi d’e-mails via Thymeleaf.

## Sommaire
- [Stack](#stack)
- [Prérequis](#prérequis)
- [Configuration](#configuration)
- [Démarrage rapide](#démarrage-rapide)
- [Exécution locale (sans Docker)](#exécution-locale-sans-docker)
- [Authentification et rôles](#authentification-et-rôles)
- [Points d’entrée API](#points-dentrée-api)
- [E-mails](#e-mails)
- [Build & Packaging](#build--packaging)
- [Déploiement (Checklist)](#déploiement-checklist)
- [Maintenance & Qualité](#maintenance--qualité)
- [Licence](#licence)

## Stack
- Java 17, Spring Boot 3.2.x (Web, Data JPA, Security, Validation, Cache, Mail, Thymeleaf)
- Base de données: MySQL 8
- Cache/Messaging: Redis (optionnel)
- Auth: JWT (JJWT)
- Documentation: springdoc OpenAPI (Swagger UI)
- Docker: multi-stage (Maven builder + JRE 17)

## Prérequis
- Option Docker: Docker Desktop (Windows/Mac/Linux)
- Option locale: Java 17 JDK (Temurin recommandé), Maven 3.9+

## Configuration
Les propriétés applicatives principales se trouvent dans `src/main/resources/application.yml`.

Variables d’environnement clés:
- Base de données
  - `SPRING_DATASOURCE_URL` (ex: `jdbc:mysql://localhost:3306/plateforme_ecommerce`)
  - `SPRING_DATASOURCE_USERNAME`
  - `SPRING_DATASOURCE_PASSWORD`
- JWT
  - `JWT_SECRET` (obligatoire en prod; secret robuste 256 bits)
- Mail (SMTP)
  - `SPRING_MAIL_HOST`, `SPRING_MAIL_PORT`
  - `SPRING_MAIL_USERNAME`, `SPRING_MAIL_PASSWORD`
  - `SPRING_MAIL_PROPERTIES_MAIL_SMTP_AUTH` (`true` en prod)
  - `SPRING_MAIL_PROPERTIES_MAIL_SMTP_STARTTLS_ENABLE` (`true` en prod)
  - `APP_EMAIL_FROM` ou `app.email.from` via YAML
- Redis (optionnel)
  - `SPRING_REDIS_HOST` (ex: `localhost`)

Des exemples prêts à l’emploi sont fournis dans:
- `src/main/resources/application-local.yml.example`
- `src/main/resources/application-prod.yml.example`

## Démarrage rapide

### Avec Docker Compose (recommandé pour dev)
Dans `wave/`:
```bash
docker compose up --build
```
Services exposés:
- API: http://localhost:8080/api
- Swagger UI: http://localhost:8080/api/swagger-ui.html
- MySQL: 3306 (volume persistant `mysql_data`)
- Redis: 6379
- Mailhog (dev SMTP): UI http://localhost:8025 · SMTP 1025

Arrêt:
```bash
docker compose down
```
Reset DB (supprime volume):
```bash
docker compose down -v
```

## Exécution locale (sans Docker)
1) Installer Java 17 + Maven, démarrer MySQL local, optionnellement Redis et Mailhog.
2) Configurer les variables d’env (PowerShell Windows):
```powershell
$env:SPRING_DATASOURCE_URL="jdbc:mysql://localhost:3306/plateforme_ecommerce"
$env:SPRING_DATASOURCE_USERNAME="root"
$env:SPRING_DATASOURCE_PASSWORD="password"
$env:JWT_SECRET="remplace-par-un-secret-long-256-bits"
# Optionnel
# $env:SPRING_REDIS_HOST="localhost"
# $env:SPRING_MAIL_HOST="localhost"
# $env:SPRING_MAIL_PORT="1025"
```
3) Build & run:
```bash
mvn clean package -DskipTests
java -jar target/plateforme-api-0.0.1-SNAPSHOT.jar
```

## Authentification et rôles
- Auth JWT: `POST /api/auth/login` → `{ token }`, puis header `Authorization: Bearer <token>`
- Rôles et accès (voir `src/main/java/com/plateforme/config/SecurityConfig.java`):
  - Public: `/api/public/**`, `/api/auth/**`, Swagger (`/swagger-ui.html`, `/swagger-ui/**`, `/v3/api-docs/**`)
  - Client: `/api/client/**` (actuellement permis par défaut)
  - Admin: `/api/admin/**`
  - Gérant: `/api/gerant/**` (Admin autorisé)
  - Livreur: `/api/livreur/**` (Admin/Gérant autorisés)

## Points d’entrée API
Base path: `/api` (config `server.servlet.context-path`)

Exemples:
- Auth
  - `POST /api/auth/login` — body `{ email, password }` → `{ token }`
  - `POST /api/auth/refresh`, `POST /api/auth/logout`
- Public
  - `GET /api/public/vagues/a-venir`, `GET /api/public/produits/vedettes`, `GET /api/public/categories`
- Client
  - `GET /api/client/vagues/actives`
  - `GET /api/client/vagues/{vagueId}/produits`
  - `POST /api/client/commandes?vagueId=UUID&clientEmail=...`
- Admin
  - `POST /api/admin/gerants`, `POST /api/admin/livreurs` — body `CreateUserRequest`
  - `GET /api/admin/utilisateurs`
- Gérant
  - `POST /api/gerant/vagues` — body `CreateVagueRequest`
  - `PUT /api/gerant/vagues/{id}/produits` — body `List<UUID>`
  - `POST /api/gerant/produits` — body `CreateProduitRequest`
  - `POST /api/gerant/promotions` — body `CreatePromotionRequest`
  - `POST /api/gerant/packages` — body `CreatePackageRequest`
  - `GET /api/gerant/vagues/{vagueId}/rapport`
  - `GET /api/gerant/commandes?vagueId=<UUID?>`
- Livreur
  - `GET /api/livreur/livraisons`
  - `POST /api/livreur/livraisons/{id}/scanner-qr` — body `ScanQRRequest`
  - `PUT /api/livreur/livraisons/{id}/statut?statut=...`
  - `POST /api/livreur/livraisons/{id}/confirmer` — body `ConfirmationLivraisonRequest`

Swagger UI: `GET /api/swagger-ui.html`

## E-mails
- Dev: Mailhog (port 1025) — configuré via docker-compose
- Prod: fournir un SMTP réel (AUTH + STARTTLS), `app.email.from` avec domaine valide
- Templates: `src/main/resources/templates/emails/*`

## Build & Packaging
- Maven: `mvn clean package -DskipTests` → JAR exécutable dans `target/`
- Docker (multi-stage): voir `Dockerfile`
- Docker Compose: `docker-compose.yml` (MySQL, Redis, Mailhog, App)

## Déploiement (Checklist)
- Secrets via variables d’environnement (ne pas committer): `JWT_SECRET`, credentials DB/SMTP
- Profil prod recommandé (voir l’exemple prod):
  - Logs: INFO/WARN
  - Swagger: désactivé ou protégé
  - JPA: `ddl-auto=validate` (préférer migrations gérées)
- Santé/monitoring: `GET /api/health`, `GET /api/health2`
- Base de données: utilisateur dédié, mots de passe forts, sauvegardes

## Maintenance & Qualité
- Conventions: UTF-8, fins de ligne LF (cf. `.editorconfig`)
- Ignorer fichiers build/IDE/temp (cf. `.gitignore`)
- Migrations DB: prévoir Flyway/Liquibase si nécessaire

## Licence
Ce projet est sous licence MIT. Voir `LICENSE`.
