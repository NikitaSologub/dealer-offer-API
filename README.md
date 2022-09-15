Swagger UI (локально)
http://localhost:15073/dealer-offer-api/swagger-ui.html

Swagger docs (локально)
http://localhost:15073/dealer-offer-api/v2/api-docs

RabbitMQ UI:
(local)      http://localhost:15672/#/
(dev)        http://rbmqtest-01-msk.inner.alfaleasing.ru:15672/

minIO UI:
(local)      http://localhost:9001/buckets
(dev)        http://minio.k8s-dev.yc.alfaleasing.ru/
(prod)       https://minio.yc.alfaleasing.ru/

    //Название exchange в которую ложим: dealer_offer_api
    //Название queue в которую свяжем с exchange dealer_offer_api: dealer_offer_api.to.stock-service.new-offers-list
    //Название routing_key для их связывания: new-offers-list.event.create