#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
INPUT_FILE="${1:-$ROOT_DIR/env.json.enc}"
OUTPUT_FILE="${2:-$ROOT_DIR/env.json}"
PASSPHRASE="${ENV_JSON_PASSPHRASE:-}"

if [[ -z "${PASSPHRASE}" ]]; then
  echo "ENV_JSON_PASSPHRASE is required."
  echo "Usage: ENV_JSON_PASSPHRASE=... $0 [input_enc] [output_json]"
  exit 1
fi

if [[ ! -f "${INPUT_FILE}" ]]; then
  echo "Input file not found: ${INPUT_FILE}"
  exit 1
fi

openssl enc -d -aes-256-cbc -pbkdf2 -a \
  -in "${INPUT_FILE}" \
  -out "${OUTPUT_FILE}" \
  -pass "pass:${PASSPHRASE}"

echo "Decrypted ${INPUT_FILE} -> ${OUTPUT_FILE}"
