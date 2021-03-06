package org.kodein.di

import kotlin.reflect.KClass

@PublishedApi
internal class JSTypeToken<T>(type: KClass<*>) : AbstractKClassTypeToken<T>(type) {

    override fun simpleErasedDispString(): String = type.simpleName ?: type.js.name

    override fun fullErasedDispString() = type.js.name

}

@Suppress("UNCHECKED_CAST", "UNCHECKED_CAST_TO_NATIVE_INTERFACE")
actual inline fun <reified T> erased(): TypeToken<T> {
    try {
        @Suppress("UNCHECKED_CAST_TO_EXTERNAL_INTERFACE")
        return JSTypeToken(T::class)
    }
    catch (ex: Throwable) {
        throw IllegalArgumentException("Could not get KClass. Note that Kotlin does NOT support reflection over primitives.")
    }
}

/**
 * Gives a [TypeToken] representing the given `Class`.
 */
// Deprecated since 6.3
@Deprecated("Use with KClass", ReplaceWith("TT(cls.kotlin)"))
fun <T: Any> TT(cls: JsClass<T>): TypeToken<T> = TT(cls.kotlin)

/**
 * Gives a [TypeToken] representing the given `KClass`.
 */
fun <T: Any> TT(cls: KClass<T>): TypeToken<T> = JSTypeToken(cls)

actual fun <T: Any> TTOf(obj: T): TypeToken<out T> = JSTypeToken<T>(obj::class)
