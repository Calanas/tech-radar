# ./decrypt.sh "SECRET_PASSWORD"
!/bin/sh

CREDS_PATH="./resources"
ENCRYPTED=$CREDS_PATH/"$(basename $CREDS_PATH/*.gpg)"

gpg --quiet --batch --yes --decrypt --passphrase="$ENCRYPT_PASSWORD" \
--output $CREDS_PATH/$(basename $ENCRYPTED .gpg) $ENCRYPTED
