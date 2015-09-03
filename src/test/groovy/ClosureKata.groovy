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

	class ClosureHolder {

		def x = 1
		def closure = {
			x + it
		}

	}

	def "Variables in a closure retain their scope"() {
		setup: "Defining a parameter with which we call the closure"
		def y = 3
		ClosureHolder holder = new ClosureHolder()

		when: "Overwriting the the x field of the closure"
		def x = 5
//		holder.

		then:
		holder.closure(y)== x + y
	}

	def "Call a closure which has a default value defined."() {
		setup:
		def closure = { int x = 2 -> ++x }

		when:
		def param = 3;

		then:
		5 == closure(3)
	}

	def "Call a closure which has a default value defined without a param."() {
		setup:
		def expctedResult = 2
		def closure = { int x = 2 -> ++x }

		expect:
		expctedResult == closure()
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
