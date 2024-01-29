# Clean and build with Maven
mvn clean package
# Build the Docker image
docker stop ftpservice-container
docker rm ftpservice-container
docker rmi ftpservice-image
docker build -t ftpservice-image .
# Run the Docker container

# Get the local IPv4 address
$localIP = (Get-NetIPAddress | Where-Object { $_.AddressFamily -eq 'IPv4' -and $_.PrefixOrigin -eq 'Dhcp' }).IPAddress

docker run --name ftpservice-container -d -e HOSTNAME="$localIP" -e SERVICE_HTTPS_ENABLED=false -e FTP_REMOTEPATH=/achau.ch/ -p 8082:8082 ftpservice-image