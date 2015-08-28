import spock.lang.Specification

/**
 * Created by pbayer.*/
class ClosureKata extends Specification {

	def "Simple closure"() {
		setup:
		def closure = { return "HelloWorld!" }


		expect:
		closure() == "HelloWorld!"
	}

	def "Call a closure with a parameter"() {
		setup:
		def closure = { int x -> return x + 1 }

		when:
		def result = closure()

		then:
		result == 3
	}

	def "Curry a closure"() {
		setup:
		def closure = { int x, int y -> return x + y + 1 }

		when:
		def curriedClosure = closure.curry(1)
		def result = curriedClosure()

		then:
		result == 4
	}

}
