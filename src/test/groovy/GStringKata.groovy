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

		when: 'Using alternative syntax'
		gString = "This is the value of $someVar as string"

		then:
		gString.contains("12321")
	}

	def "Add two numbers"() {
		setup:
		def x = 1
		def y = 2

		when:
		def gString = "${x}+${y}".toInteger()

		then:
		gString == x + y
	}

	def "call a function"() {
		setup:
		def aString = "foo"
		def bString = "Bar"

		when:
		def gString = "${aString}.concat(bString)"

		then:
		gString == "fooBar"
	}

	def "call a closure"() {
		setup:
		def funct = { "fooBar" }

		when:
		def gString = "${funct}"

		then:
		gString == "fooBar"
	}

	def "access list elements"() {
		setup:
		List<String> strings = []
		strings << 'aString'
		strings << 'bString'

		when:
		def gString = "${strings}"

		then:
		gString == "bString"
	}

}
