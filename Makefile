db-up:
	docker-compose up -d --force-recreate

db-down:
	docker-compose down -v

mac-db-up:
	/Applications/Docker.app/Contents/Resources/bin/docker compose up -d --force-recreate

mac-db-down:
	/Applications/Docker.app/Contents/Resources/bin/docker compose down -v