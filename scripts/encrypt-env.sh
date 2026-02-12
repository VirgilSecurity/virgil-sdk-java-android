#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
INPUT_FILE="${1:-$ROOT_DIR/env.json}"
OUTPUT_FILE="${2:-$ROOT_DIR/env.json.enc}"
PASSPHRASE="${ENV_JSON_PASSPHRASE:-}"

if [[ -z "${PASSPHRASE}" ]]; then
  echo "ENV_JSON_PASSPHRASE is required."
  echo "Usage: ENV_JSON_PASSPHRASE=... $0 [input_json] [output_enc]"
  exit 1
fi

if [[ ! -f "${INPUT_FILE}" ]]; then
  echo "Input file not found: ${INPUT_FILE}"
  exit 1
fi

openssl enc -aes-256-cbc -pbkdf2 -salt -a \
  -in "${INPUT_FILE}" \
  -out "${OUTPUT_FILE}" \
  -pass "pass:${PASSPHRASE}"

echo "Encrypted ${INPUT_FILE} -> ${OUTPUT_FILE}"
