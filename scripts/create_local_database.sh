#!/usr/bin/env bash

set -euxo pipefail

echo "creating databases if it doesn't exit yet..."
mysql -uroot -e "CREATE DATABASE IF NOT EXISTS db_spring_sandbox;"