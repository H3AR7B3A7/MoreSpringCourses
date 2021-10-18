#!/bin/bash
echo --- Setting up MySQL and phpMyAdmin docker containers ---
docker-compose up -d
echo --- Done! ---
exec "$SHELL"