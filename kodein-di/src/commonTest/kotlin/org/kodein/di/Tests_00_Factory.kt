package org.kodein.di

import org.kodein.di.test.*
import kotlin.reflect.KClass
import kotlin.test.*

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class Tests_00_Factory {

    @Test
    fun test_00_FactoryBindingGetFactory() {
        val di = DI {
            bind() from factory { name: String -> Person(name) }
        }

        val p1: (String) -> Person by di.factory()
        val p2: (String) -> Person by di.factory()

        assertNotSame(p1("Salomon"), p2("Salomon"))
    }

    @Test
    fun test_01_WithFactoryGetProvider() {

        val di = DI { bind<Person>() with factory { name: String -> Person(name) } }

        val p: () -> Person by di.provider(arg = "Salomon")
        val dp: () -> Person = di.direct.provider(arg = "Salomon")

        assertAllEqual("Salomon", p().name, dp().name)

        val fp: () -> Person by di.provider(fArg = { "Salomon" })
        val dfp: () -> Person = di.direct.provider(fArg = { "Salomon" })

        assertAllEqual("Salomon", fp().name, dfp().name)
    }

    @Test
    fun test_02_WithFactoryGetInstance() {

        val di = DI { bind<Person>() with factory { name: String -> Person(name) } }

        val p: Person by di.instance(arg = "Salomon")

        assertEquals("Salomon", p.name)

        val fp: Person by di.instance(fArg = { "Salomon" })

        assertEquals("Salomon", fp.name)
    }

    @Test
    fun test_03_WithGenericFactoryGetInstance() {

        val di = DI { bind<Person>() with factory { l: List<*> -> Person(l.first().toString()) } }

        val p: Person by di.instance(arg = listOf("Salomon", "BRYS"))

        assertEquals("Salomon", p.name)
    }

    @Test
    fun test_04_WithTwoItfFactoryGetInstance() {

        val di = DI {
            bind<Person>() with factory { p: IName -> Person(p.firstName) }
            bind<Person>() with factory { p: IFullName -> Person(p.firstName + " " + p.lastName) }
        }

        val p: Person by di.instance(arg = FullInfos("Salomon", "BRYS", 30))

        assertFailsWith<DI.NotFoundException> { p.name }
    }

    @Test
    fun test_05_WithFactoryLambdaArgument() {
        val di = DI {
            bind<() -> Unit>() with factory { f: () -> Unit -> f }
        }

        var passed = false
        val f = { passed = true }

        val run: () -> Unit by di.instance(arg = f)
        run.invoke()

        assertTrue(passed)
    }

    interface FakeLogger { val cls: KClass<*> }

    class FakeLoggerImpl(override val cls: KClass<*>) : FakeLogger

    class AwareTest(override val di: DI) : DIAware {
        val logger: FakeLogger by instance(arg = this::class)
    }

    @Test
    fun test_06_StarFactory() {
        val di = DI {
            bind<FakeLogger>() with factory { cls: KClass<*> -> FakeLoggerImpl(cls) }
        }

        val logger: FakeLogger by di.instance(arg = AwareTest::class)
        assertEquals(AwareTest::class, logger.cls)

        val test = AwareTest(di)
        assertEquals(AwareTest::class, test.logger.cls)
    }


}
