echo "Starting services"
java -jar service/eureka-service-0.0.1-SNAPSHOT.jar &
sleep 8
java -jar service/zuul-service-0.0.1-SNAPSHOT.jar &
java -jar service/credit-service-0.0.1-SNAPSHOT.jar &
java -jar service/db-service-h2-0.0.1-SNAPSHOT.jar &
java -jar service/transaction-service-0.0.1-SNAPSHOT.jar &
java -jar service/user-service-0.0.1-SNAPSHOT.jar &
echo "Stopping services"