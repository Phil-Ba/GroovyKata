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

		/**
		 * Returns the this of the closure
		 * */
		def thisClosure = {
			this
		}

		/**
		 * Returns the owner of the inner closure
		 * */
		def ownerClosure = {
			def ownerInnerClosure = {
				owner
			}
			ownerInnerClosure()
		}

		/**
		 * Returns the delegate of the closure
		 * */
		def delegateClosure = {
			delegate
		}

	}

	def closureHolder = new ClosureHolder()

	def "Simple closure delegation"() {
		expect: "this refers to the closureHolder"
		closureHolder.thisClosure() == closureHolder
		and:
		"owner refers to the enclosing object/closure. Since this closure returns the owner of an inner closure, the owner should be this " +
				"closure"
		closureHolder.ownerClosure() == closureHolder.ownerClosure
		and: "the owners of outer and inner closure are different"
		closureHolder.ownerClosure() != closureHolder.ownerClosure.owner
		and: "the default delegate is the owner"
		closureHolder.delegateClosure() == closureHolder.delegateClosure.owner
	}

	def "Changing the delegate of a closure"() {
		setup:
		String foo = 'goliath'
		def closure = { delegate.toUpperCase() }

		when: 'we change the delegate of the closure'
		closure.owner = foo

		then: 'toUpperCase should be invoked on the  delegate'
		closure() == 'GOLIATH'
	}

	class Vendor {

		String product

		def scream = {
			product.toUpperCase() + '!'
		}
	}

	def "The lexical scope of closures is the scope of their owner"() {
		setup:
		Vendor vendor = new Vendor(product: 'cabbage')

		when: 'We change the product'

		then:
		vendor.scream() == 'ICE CREAM!'
	}

	class ADelegate {

		int anInt
	}

	def "If a closure doesnt find a variable it tries to find it in the delegate"() {
		setup:
		def aClosure = {
			anInt++
		}
		def aDelegate = new ADelegate()

		when: 'We setup the delegate and closure properly'


		then: 'anInt should resolve to the property in the delegate'
		aClosure() == 23
	}

	def "Delegation only works if the closure cant find the variable"() {
		setup:
		def anInt = 5
		def aClosure = {
			anInt++
		}
		aClosure.resolveStrategy = Closure.DELEGATE_ONLY
		def aDelegate = new ADelegate(anInt: 23)
		aClosure.delegate = aDelegate

		when: 'We expect the result to be?'
		def expecedResult = 24

		then: 'anInt should resolve to the property in the delegate'
		aClosure() == expecedResult
	}

}
