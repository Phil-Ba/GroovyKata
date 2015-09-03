import spock.lang.Specification

/**
 * Created by pbayer.*/
class ClosureDelegationKata extends Specification {

	int x = 6

	class ClosureHolder {

		int x = 1
		def closure = {
			int x = 2
			x
		}

		def thisClosure = {
			this
		}

		def ownerClosure = {
			def ownerInnerClosure = {
				owner
			}
			ownerInnerClosure()
		}

		def delegateClosure = {
			delegate
		}

	}

	def closureHolder = new ClosureHolder()

	def "Simple closure delegation"() {
		expect: "this refers to the closureHolder"
		closureHolder.thisClosure() == closureHolder
		and: "owner refers to the enclosing object/closure"
		closureHolder.ownerClosure() == closureHolder.ownerClosure
		and: "the default delegate is the owner"
		closureHolder.delegateClosure() == closureHolder.delegateClosure.owner
	}

	def "Changing the closure delegate"() {
		setup:
		int foo = 99
		closureHolder.delegateClosure.delegate = foo
		def expectedDelegate = closureHolder.delegateClosure.owner

		expect:
		closureHolder.delegateClosure() == expectedDelegate
	}

	def "Closures delegate it they dont have a property"() {
		setup:
		def outerProperty = -1
		def closure = { outerProperty }

		expect:
		closure() == outerProperty
		closure.resolveStrategy == Closure.OWNER_FIRST
	}

	class Person {

		int age
	}

	def "Set the delegate to resolve to the delegate's property"() {
		setup:
		def delegate = new Person(age: 99)
		def closure = { age }

		expect:
		closure() == 99
	}

	def "Change the property resolution so that the delegate's property is used"() {
		//		def age = -1
		//		def closure = { -> age }
		//		def delegate = new Person(age: 99)
		//		closure.delegate = delegate
		//		closure.resolveStrategy == Closure.TO_SELF
		//
		//		assert closure() == 99
		expect:
		assert test().call() == 99
		true
	}

	def test() {
		def age = -1
		def closure = { -> age }
		closure.delegate = new Person(age: 99)
		closure.resolveStrategy == Closure.DELEGATE_ONLY
		closure
	}
}
