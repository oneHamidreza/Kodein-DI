package org.kodein.di.bindings

import org.kodein.di.DI
import org.kodein.di.TypeToken

/**
 * Binding that holds multiple bindings for subtypes of [T].
 *
 * @param C The context type of all bindings in the set.
 * @param A The argument type of all bindings in the set.
 * @param T The provided type of all bindings in the set.
 * @param block A function that provides a binding for each subtype.
 */
class SubTypes<C: Any, A, T : Any>(override val contextType: TypeToken<in C>, override val argType: TypeToken<in A>, override val createdType: TypeToken<out T>, val block: (TypeToken<out T>) -> DIBinding<in C, in A, out T>): DIBinding<C, A, T> {

    private val bindings = HashMap<TypeToken<out T>, DIBinding<in C, in A, out T>>()

    override fun getFactory(di: BindingDI<C>, key: DI.Key<C, A, T>): (A) -> T {
        @Suppress("UNCHECKED_CAST")
        val binding = bindings.getOrPut(key.type) { block(key.type) } as Binding<C, A, T>
        return binding.getFactory(di, key)
    }

    override fun factoryName() = "subTypesBindings"

    override val supportSubTypes: Boolean get() = true
}

/**
 * Second part of the `bind<Type>().inSet() with binding` syntax.
 *
 * @param T The type of the binding in the set.
 */
class TypeBinderSubTypes<T: Any> internal constructor(private val _binder: DI.Builder.TypeBinder<T>) {

    /**
     * Second part of the `bind<Type>().inSet() with binding` syntax.
     *
     * @param C The context type of the binding.
     * @param binding The binding to add in the set.
     */
    @Suppress("UNCHECKED_CAST", "FunctionName")
    fun <C: Any, A> With(contextType: TypeToken<in C>, argType: TypeToken<in A>, createdType: TypeToken<out T>, block: (TypeToken<out T>) -> DIBinding<in C, in A, out T>) {
        _binder with SubTypes(contextType, argType, createdType, block)
    }
}

/**
 * Allows to bind in an existing set binding (rather than directly as a new binding).
 *
 * First part of the `bind<Type>().inSet() with binding` syntax.
 *
 * @param T The provided type of all bindings in the set.
 * @param setTypeToken The type of the bound set.
 */
@Suppress("FunctionName")
fun <T: Any> DI.Builder.TypeBinder<T>.subTypes() = TypeBinderSubTypes(this)
