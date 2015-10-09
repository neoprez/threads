class Client < Thread
	def initialize
		super
	end

	def self.start
		puts "Hey"
		new {
			super
		}
		puts
	end
end
