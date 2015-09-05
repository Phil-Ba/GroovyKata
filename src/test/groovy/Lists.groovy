import spock.lang.Specification

/**
 * Created by pbayer.*/
class Lists extends Specification {

	def 'Intro'() {
		setup: 'Lets start with an empty list'
		List<String> aList = []

		when: 'and add an item with <<'
		aList << 'aString'

		then: 'the list contains the new item'
		aList.contains('aString')

		when: 'we add an item with +'
		List<String> bList = aList + 'bString'

		then: 'a new list is created'
		aList != bList
		and: 'only the new list contains the added item'
		!aList.contains('bString')
		bList.contains('aString')
		bList.contains('bString')

		when: 'removing only works with - (no >>)'
		bList = bList - 'bString'
		//alternative syntax: bList -= 'bString'

		then: 'the list doesnt contain the removed item'
		!bList.contains('bString')
		and: 'list are compare by their content btw'
		bList == aList
	}

	def 'Apply a closure to each element'() {
		setup:
		List aList = ['aString', -1, 'bString']
		List anotherList = []

		when: 'we write a closure which puts the string into anotherList'
		def closure = {
			//TODO
		}

		then: 'we apply the closure to each element in the list'
		aList.each closure
		then: 'anotherList should contain only strings'
		anotherList == ['aString', 'bString']
		and: 'the original list is still the same'
		aList == ['aString', -1 ,'bString']
	}

	def 'Collect basically works the same way as each, but returns a list containing all values'() {
		setup:
		List aList = ['hulk', 'goliath', 'godzilla']

		when: 'we write a closure which uppercases each item'
		def closure = {
			//TODO
		}

		then: 'we apply the closure to each element in the list'
		List anotherList = aList.collect closure
		and: 'the original list is still the same'
		aList == ['HULK', 'GOLIATH' ,'GODZILLA']
	}

	def ''() {
		setup:
		println ''
	}

}
