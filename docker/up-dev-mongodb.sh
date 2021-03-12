#!/bin/bash
docker-compose -f ../docker-compose.yml -f ../docker/mongodb/docker-compose.yml -f ../docker/portainer/docker-compose.yml up -d portainer mongo mongo-express