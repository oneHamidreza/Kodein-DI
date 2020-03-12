package org.kodein.di.internal

import org.kodein.di.*

@Suppress("UNCHECKED_CAST")
private inline val DIContext<*>.anyType get() = type as TypeToken<in Any>

@Deprecated(DEPRECATED_KODEIN_7X, ReplaceWith("DirectDIBaseImpl"), DeprecationLevel.ERROR)
internal typealias DirectKodeinBaseImpl = DirectDIBaseImpl

@Suppress("FunctionName")
internal abstract class DirectDIBaseImpl protected constructor(override val container: DIContainer, val context: DIContext<*>) : DirectDI {

    override val directDI: DirectDI get() = this

    override val lazy: DI get() = DIImpl(container as DIContainerImpl).On(context = context)

    override fun On(context: DIContext<*>): DirectDI = DirectDIImpl(container, context)

    override fun <A, T : Any> Factory(argType: TypeToken<in A>, type: TypeToken<T>, tag: Any?): (A) -> T = container.factory(DI.Key(context.anyType, argType, type, tag), context.value)

    override fun <A, T : Any> FactoryOrNull(argType: TypeToken<in A>, type: TypeToken<T>, tag: Any?): ((A) -> T)? = container.factoryOrNull(DI.Key(context.anyType, argType, type, tag), context.value)

    override fun <T : Any> Provider(type: TypeToken<T>, tag: Any?): () -> T = container.provider(DI.Key(context.anyType, UnitToken, type, tag), context.value)

    override fun <A, T : Any> Provider(argType: TypeToken<in A>, type: TypeToken<T>, tag: Any?, arg: () -> A): () -> T = container.factory(DI.Key(context.anyType, argType, type, tag), context.value).toProvider(arg)

    override fun <T : Any> ProviderOrNull(type: TypeToken<T>, tag: Any?): (() -> T)? = container.providerOrNull(DI.Key(context.anyType, UnitToken, type, tag), context.value)

    override fun <A, T : Any> ProviderOrNull(argType: TypeToken<in A>, type: TypeToken<T>, tag: Any?, arg: () -> A): (() -> T)? = container.factoryOrNull(DI.Key(context.anyType, argType, type, tag), context.value)?.toProvider(arg)

    override fun <T : Any> Instance(type: TypeToken<T>, tag: Any?): T = container.provider(DI.Key(context.anyType, UnitToken, type, tag), context.value).invoke()

    override fun <A, T : Any> Instance(argType: TypeToken<in A>, type: TypeToken<T>, tag: Any?, arg: A): T = container.factory(DI.Key(context.anyType, argType, type, tag), context.value).invoke(arg)

    override fun <T : Any> InstanceOrNull(type: TypeToken<T>, tag: Any?): T? = container.providerOrNull(DI.Key(context.anyType, UnitToken, type, tag), context.value)?.invoke()

    override fun <A, T : Any> InstanceOrNull(argType: TypeToken<in A>, type: TypeToken<T>, tag: Any?, arg: A): T? = container.factoryOrNull(DI.Key(context.anyType, argType, type, tag), context.value)?.invoke(arg)
}
@Deprecated(DEPRECATED_KODEIN_7X, ReplaceWith("DirectDIImpl"), DeprecationLevel.ERROR)
internal typealias DKodeinImpl = DirectDIImpl
internal expect class DirectDIImpl(container: DIContainer, context: DIContext<*>) : DirectDI
