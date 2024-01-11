call mvn clean package
call docker rmi ftpservice-image
call docker build -t ftpservice-image .
call docker tag ftpservice-image ghcr.io/lienv1/webshopproject2/ftpservice-image:version1.0.1
call docker push ghcr.io/lienv1/webshopproject2/ftpservice-image:version1.0.1