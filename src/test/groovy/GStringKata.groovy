import spock.lang.Specification

/**
 * Created by pbayer.*/
class GStringKata extends Specification {

	def "String template at work"() {
		setup:
		def someVar = 12321
		def gString = "This is the value of ${someVar} as string"
		def normalString = 'This is the value of ${someVar} as string'

		expect:
		gString.contains("12321")
		!normalString.contains("12321")
	}

	def "Add two numbers"() {
		setup:
		def x = 1
		def y = 2

		expect:
		"${x}+${y}".toInteger() == x + y
	}

	def "call a closure"() {
		setup:
		def funct = {"fooBar"}

		expect:
		"${funct}" == "fooBar"
	}

}
