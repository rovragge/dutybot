docker run -d --restart=always --name app-dutybot \
  --network dev-env \
  -e TZ=Europe/Moscow \
  -e DB_HOST=postgres -e DB_NAME=postgres -e DB_SCHEMA=schema \
  -e DB_USER=user -e DB_PASS=pass \
  -e BOT_TOKEN='token' \
  -e TG_CHATS='chat1,-chat2' \
  -e ANNOUNCE_CRON='0 0 10 * * ?' \
  -p 18080:8080 \
  image