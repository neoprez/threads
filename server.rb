require 'socket'

HOST = '127.0.0.1'
PORT = 5000 

#begin
server 			= Socket.new Socket::AF_INET, Socket::SOCK_STREAM
socketAddr 	=  Socket.pack_sockaddr_in(PORT, HOST)
server.bind socketAddr
server.listen(5)

puts "Serving on localhost port 5000"

def listen_to_connections(server)
	client_socket, client_addrinfo = server.accept

	puts "The client said, '#{client_socket.readline.chomp}'"
	client_socket.write("HTTP/1.1 200 OK\r\n\r\n")
	client_socket.puts "Hello from script one!"
	client_socket.close
end

begin
	loop do
		client_socket, client_addrinfo = server.accept
		Thread.start {
			puts "Accepted incomming connection from: " + client_addrinfo.inspect
			puts "The client said, '#{client_socket.readline.chomp}'"
			client_socket.write("HTTP/1.1 200 OK\r\n\r\n")
			client_socket.puts "Hello from script one!"
			client_socket.close
		}
	end

rescue IO::WaitReadable, Errno::EINTR
	IO.select([server])
	retry
end

server.close

