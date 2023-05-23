# sample-spring-boot-docker-compose

### 設定
1. docker-compose.ymlで指定するポートを重複しないように変更指定ください。
2. application.ymlの `server.port` を重複しないように変更指定ください。

### 実行
下記のコマンドを実行すると `docker compose` と `flywayMigrate` が実行され、その後アプリが起動します。
```shell
gradlew bootrun
```

### 実行サンプル
* ユーザーの登録
```shell
curl -X POST --location "http://127.0.0.1:8080/sample/entry" \
    -H "Content-Type: application/json" \
    -d "{
          \"loginid\": \"hoge\",
          \"name\": \"hoge\"
        }"
```

* ユーザーの取得
```shell
curl -X POST --location "http://localhost:8080/sample/${id}"
```