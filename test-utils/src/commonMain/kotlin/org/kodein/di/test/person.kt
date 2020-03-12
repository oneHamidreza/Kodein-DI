package org.kodein.di.test

interface IPerson { val name: String? }

data class Person(override val name: String? = null ) : IPerson

data class A(var b: B?, val str: String = "")
data class B(var c: C?, val int: Int = 0)
data class C(var a: A?, val char: Char = ' ')
data class D(val str: String = "") {
    lateinit var e: E
}
data class E(val int: Int = 0) {
    lateinit var f: F
}
data class F(val char: Char = ' ') {
    lateinit var d: D
}

interface IName {
    val firstName: String
}

open class Name(override val firstName: String) : IName {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Name) return false
        if (firstName != other.firstName) return false
        return true
    }

    override fun hashCode(): Int{
        return firstName.hashCode()
    }

    override fun toString() = firstName
}

interface IFullName : IName {
    val lastName: String
}

open class FullName(firstName: String, override val lastName: String) : Name(firstName), IFullName {
    override fun equals(other: Any?): Boolean{
        if (this === other) return true
        if (other !is FullName) return false
        if (!super.equals(other)) return false
        if (lastName != other.lastName) return false
        return true
    }

    override fun hashCode(): Int {
        return 31 * super.hashCode() + lastName.hashCode()
    }

    override fun toString() = "$firstName $lastName"
}

interface IAge {
    val age: Int
}

class FullInfos(firstName: String, lastName: String, override val age: Int) : FullName(firstName, lastName), IAge {
    override fun equals(other: Any?): Boolean{
        if (this === other) return true
        if (other !is FullInfos) return false
        if (!super.equals(other)) return false
        if (age != other.age) return false
        return true
    }

    override fun hashCode(): Int {
        return 31 * super.hashCode() + age.hashCode()
    }

    override fun toString() = "$firstName $lastName, $age"
}

typealias PersonEntry = Pair<String, Person>
typealias PersonEntries = Set<PersonEntry>
