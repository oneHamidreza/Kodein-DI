package org.kodein.di.generic

import org.kodein.di.*

/**
 * Gets a factory of `T` for the given argument type, return type and tag.
 *
 * A & T generics will be preserved!
 *
 * @param A The type of argument the factory takes.
 * @param T The type of object the factory returns.
 * @param tag The bound tag, if any.
 * @return A factory.
 * @throws DI.NotFoundException if no factory was found.
 * @throws DI.DependencyLoopException When calling the factory function, if the instance construction triggered a dependency loop.
 */
inline fun <reified A : Any, reified T : Any> DIAware.factory(tag: Any? = null) = Factory<A, T>(org.kodein.type.generic(), org.kodein.type.generic(), tag)

/**
 * Gets a factory of `T` for the given argument type, return type and tag, or nul if none is found.
 *
 * A & T generics will be preserved!
 *
 * @param A The type of argument the factory takes.
 * @param T The type of object the factory returns.
 * @param tag The bound tag, if any.
 * @return A factory, or null if no factory was found.
 * @throws DI.DependencyLoopException When calling the factory function, if the instance construction triggered a dependency loop.
 */
inline fun <reified A : Any, reified T : Any> DIAware.factoryOrNull(tag: Any? = null) = FactoryOrNull<A, T>(org.kodein.type.generic(), org.kodein.type.generic(), tag)

/**
 * Gets all factories that match the the given argument type, return type and tag.
 *
 * A & T generics will be preserved!
 *
 * @param A The type of argument the factories take.
 * @param T The type of object to retrieve with the factories.
 * @param tag The bound tag, if any.
 * @return A list of factories of [T].
 * @throws DI.DependencyLoopException When calling the factory, if the value construction triggered a dependency loop.
 */
inline fun <reified A : Any, reified T : Any> DIAware.allFactories(tag: Any? = null) = AllFactories<A, T>(org.kodein.type.generic(), org.kodein.type.generic(), tag)

/**
 * Gets a provider of `T` for the given type and tag.
 *
 * T generics will be preserved!
 *
 * @param T The type of object the provider returns.
 * @param tag The bound tag, if any.
 * @return A provider.
 * @throws DI.NotFoundException if no provider was found.
 * @throws DI.DependencyLoopException When calling the provider function, if the instance construction triggered a dependency loop.
 */
inline fun <reified T : Any> DIAware.provider(tag: Any? = null) = Provider<T>(org.kodein.type.generic(), tag)

/**
 * Gets a provider of [T] for the given type and tag, curried from a factory that takes an argument [A].
 *
 * A & T generics will be preserved!
 *
 * @param A The type of argument the curried factory takes.
 * @param T The type of object to retrieve with the returned provider.
 * @param tag The bound tag, if any.
 * @param arg The argument that will be given to the factory when curried.
 * @return A provider of [T].
 * @throws DI.NotFoundException If no provider was found.
 * @throws DI.DependencyLoopException When calling the provider, if the value construction triggered a dependency loop.
 */
inline fun <reified A : Any, reified T : Any> DIAware.provider(tag: Any? = null, arg: A) = Provider<A, T>(org.kodein.type.generic(), org.kodein.type.generic(), tag, { arg })

/**
 * Gets a provider of [T] for the given type and tag, curried from a factory that takes an argument [A].
 *
 * A & T generics will be preserved!
 *
 * @param A The type of argument the curried factory takes.
 * @param T The type of object to retrieve with the returned provider.
 * @param tag The bound tag, if any.
 * @param arg The argument that will be given to the factory when curried.
 * @return A provider of [T].
 * @throws DI.NotFoundException If no provider was found.
 * @throws DI.DependencyLoopException When calling the provider, if the value construction triggered a dependency loop.
 */
inline fun <A, reified T : Any> DIAware.provider(tag: Any? = null, arg: Typed<A>) = Provider<A, T>(arg.type, org.kodein.type.generic(), tag, { arg.value })

/**
 * Gets a provider of [T] for the given type and tag, curried from a factory that takes an argument [A].
 *
 * A & T generics will be preserved!
 *
 * @param A The type of argument the curried factory takes.
 * @param T The type of object to retrieve with the returned provider.
 * @param tag The bound tag, if any.
 * @param fArg A function that returns the argument that will be given to the factory when curried.
 * @return A provider of [T].
 * @throws DI.NotFoundException If no provider was found.
 * @throws DI.DependencyLoopException When calling the provider, if the value construction triggered a dependency loop.
 */
inline fun <reified A : Any, reified T : Any> DIAware.provider(tag: Any? = null, noinline fArg: () -> A) = Provider<A, T>(org.kodein.type.generic(), org.kodein.type.generic(), tag, fArg)

/**
 * Gets a provider of `T` for the given type and tag, or null if none is found.
 *
 * T generics will be preserved!
 *
 * @param T The type of object the provider returns.
 * @param tag The bound tag, if any.
 * @return A provider, or null if no provider was found.
 * @throws DI.DependencyLoopException When calling the provider function, if the instance construction triggered a dependency loop.
 */
inline fun <reified T : Any> DIAware.providerOrNull(tag: Any? = null) = ProviderOrNull<T>(org.kodein.type.generic(), tag)

/**
 * Gets a provider of [T] for the given type and tag, curried from a factory that takes an argument [A], or null if none is found.
 *
 * A & T generics will be preserved!
 *
 * @param A The type of argument the curried factory takes.
 * @param T The type of object to retrieve with the returned provider.
 * @param tag The bound tag, if any.
 * @param arg The argument that will be given to the factory when curried.
 * @return A provider of [T], or null if no factory was found.
 * @throws DI.DependencyLoopException When calling the provider, if the value construction triggered a dependency loop.
 */
inline fun <reified A : Any, reified T : Any> DIAware.providerOrNull(tag: Any? = null, arg: A) = ProviderOrNull<A, T>(org.kodein.type.generic(), org.kodein.type.generic(), tag, { arg })

/**
 * Gets a provider of [T] for the given type and tag, curried from a factory that takes an argument [A], or null if none is found.
 *
 * The argument type is extracted from the `Typed.type` of the argument.
 *
 * A & T generics will be preserved!
 *
 * @param A The type of argument the curried factory takes.
 * @param T The type of object to retrieve with the returned provider.
 * @param tag The bound tag, if any.
 * @param arg The argument that will be given to the factory when curried.
 * @return A provider of [T], or null if no factory was found.
 * @throws DI.DependencyLoopException When calling the provider, if the value construction triggered a dependency loop.
 */
inline fun <A, reified T : Any> DIAware.providerOrNull(tag: Any? = null, arg: Typed<A>) = ProviderOrNull<A, T>(arg.type, org.kodein.type.generic(), tag, { arg.value })

/**
 * Gets a provider of [T] for the given type and tag, curried from a factory that takes an argument [A], or null if none is found.
 *
 * A & T generics will be preserved!
 *
 * @param A The type of argument the curried factory takes.
 * @param T The type of object to retrieve with the returned provider.
 * @param tag The bound tag, if any.
 * @param fArg A function that returns the argument that will be given to the factory when curried.
 * @return A provider of [T], or null if no factory was found.
 * @throws DI.DependencyLoopException When calling the provider, if the value construction triggered a dependency loop.
 */
inline fun <reified A : Any, reified T : Any> DIAware.providerOrNull(tag: Any? = null, noinline fArg: () -> A) = ProviderOrNull<A, T>(org.kodein.type.generic(), org.kodein.type.generic(), tag, fArg)

/**
 * Gets all providers that match the the given return type and tag.
 *
 * T generics will be preserved!
 *
 * @param T The type of object to retrieve with the providers.
 * @param tag The bound tag, if any.
 * @return A list of providers of [T].
 * @throws DI.DependencyLoopException When calling the factory, if the value construction triggered a dependency loop.
 */
inline fun <reified T : Any> DIAware.allProviders(tag: Any? = null) = AllProviders<T>(org.kodein.type.generic(), tag)

/**
 * Gets all providers that match the the given return type and tag, curried from factories that take an argument [A].
 *
 * A & T generics will be preserved!
 *
 * @param A The type of argument the curried factories take.
 * @param T The type of object to retrieve with the providers.
 * @param tag The bound tag, if any.
 * @param arg The argument that will be given to the factory when curried.
 * @return A list of providers of [T].
 * @throws DI.DependencyLoopException When calling the factory, if the value construction triggered a dependency loop.
 */
inline fun <reified A : Any, reified T : Any> DIAware.allProviders(tag: Any? = null, arg: A) = AllProviders<A, T>(org.kodein.type.generic(), org.kodein.type.generic(), tag, { arg })

/**
 * Gets all providers that match the the given return type and tag, curried from factories that take an argument [A].
 *
 * The argument type is extracted from the `Typed.type` of the argument.
 *
 * A & T generics will be preserved!
 *
 * @param A The type of argument the curried factories take.
 * @param T The type of object to retrieve with the providers.
 * @param tag The bound tag, if any.
 * @param arg The argument that will be given to the factory when curried.
 * @return A list of providers of [T].
 * @throws DI.DependencyLoopException When calling the factory, if the value construction triggered a dependency loop.
 */
inline fun <A, reified T : Any> DIAware.allProviders(tag: Any? = null, arg: Typed<A>) = AllProviders<A, T>(arg.type, org.kodein.type.generic(), tag, { arg.value })

/**
 * Gets all providers that match the the given return type and tag, curried from factories that take an argument [A].
 *
 * A & T generics will be preserved!
 *
 * @param A The type of argument the curried factories take.
 * @param T The type of object to retrieve with the providers.
 * @param tag The bound tag, if any.
 * @param fArg A function that returns the argument that will be given to the factory when curried.
 * @return A list of providers of [T].
 * @throws DI.DependencyLoopException When calling the factory, if the value construction triggered a dependency loop.
 */
inline fun <reified A : Any, reified T : Any> DIAware.allProviders(tag: Any? = null, noinline fArg: () -> A) = AllProviders<A, T>(org.kodein.type.generic(), org.kodein.type.generic(), tag, fArg)

/**
 * Gets an instance of `T` for the given type and tag.
 *
 * T generics will be preserved!
 *
 * @param T The type of object to retrieve.
 * @param tag The bound tag, if any.
 * @return An instance.
 * @throws DI.NotFoundException if no provider was found.
 * @throws DI.DependencyLoopException If the instance construction triggered a dependency loop.
 */
inline fun <reified T : Any> DIAware.instance(tag: Any? = null) = Instance<T>(org.kodein.type.generic(), tag)

/**
 * Gets an instance of [T] for the given type and tag, curried from a factory that takes an argument [A].
 *
 * A & T generics will be preserved!
 *
 * @param A The type of argument the curried factory takes.
 * @param T The type of object to retrieve.
 * @param tag The bound tag, if any.
 * @param arg The argument that will be given to the factory when curried.
 * @return An instance of [T].
 * @throws DI.NotFoundException If no provider was found.
 * @throws DI.DependencyLoopException If the value construction triggered a dependency loop.
 */
inline fun <reified A : Any, reified T : Any> DIAware.instance(tag: Any? = null, arg: A) = Instance<A, T>(org.kodein.type.generic(), org.kodein.type.generic(), tag, { arg })

/**
 * Gets an instance of [T] for the given type and tag, curried from a factory that takes an argument [A].
 *
 * The argument type is extracted from the `Typed.type` of the argument.
 *
 * A & T generics will be preserved!
 *
 * @param A The type of argument the curried factory takes.
 * @param T The type of object to retrieve.
 * @param tag The bound tag, if any.
 * @param arg The argument that will be given to the factory when curried.
 * @return An instance of [T].
 * @throws DI.NotFoundException If no provider was found.
 * @throws DI.DependencyLoopException If the value construction triggered a dependency loop.
 */
inline fun <A : Any, reified T : Any> DIAware.instance(tag: Any? = null, arg: Typed<A>) = Instance<A, T>(arg.type, org.kodein.type.generic(), tag, { arg.value })

/**
 * Gets an instance of [T] for the given type and tag, curried from a factory that takes an argument [A].
 *
 * A & T generics will be preserved!
 *
 * @param A The type of argument the curried factory takes.
 * @param T The type of object to retrieve.
 * @param tag The bound tag, if any.
 * @param fArg A function that returns the argument that will be given to the factory when curried.
 * @return An instance of [T].
 * @throws DI.NotFoundException If no provider was found.
 * @throws DI.DependencyLoopException If the value construction triggered a dependency loop.
 */
inline fun <reified A : Any, reified T : Any> DIAware.instance(tag: Any? = null, noinline fArg: () -> A) = Instance<A, T>(org.kodein.type.generic(), org.kodein.type.generic(), tag, fArg)

/**
 * Gets an instance of `T` for the given type and tag, or null if none is found.
 *
 * T generics will be preserved!
 *
 * @param T The type of object to retrieve.
 * @param tag The bound tag, if any.
 * @return An instance, or null if no provider was found.
 * @throws DI.DependencyLoopException If the instance construction triggered a dependency loop.
 */
inline fun <reified T : Any> DIAware.instanceOrNull(tag: Any? = null) = InstanceOrNull<T>(org.kodein.type.generic(), tag)

/**
 * Gets an instance of [T] for the given type and tag, curried from a factory that takes an argument [A], or null if none is found.
 *
 * A & T generics will be preserved!
 *
 * @param A The type of argument the curried factory takes.
 * @param T The type of object to retrieve.
 * @param tag The bound tag, if any.
 * @param arg The argument that will be given to the factory when curried.
 * @return An instance of [T], or null if no factory was found.
 * @throws DI.DependencyLoopException If the value construction triggered a dependency loop.
 */
inline fun <reified A : Any, reified T : Any> DIAware.instanceOrNull(tag: Any? = null, arg: A) = InstanceOrNull<A, T>(org.kodein.type.generic(), org.kodein.type.generic(), tag, { arg })

/**
 * Gets an instance of [T] for the given type and tag, curried from a factory that takes an argument [A], or null if none is found.
 *
 * The argument type is extracted from the `Typed.type` of the argument.
 *
 * A & T generics will be preserved!
 *
 * @param A The type of argument the curried factory takes.
 * @param T The type of object to retrieve.
 * @param tag The bound tag, if any.
 * @param arg The argument that will be given to the factory when curried.
 * @return An instance of [T], or null if no factory was found.
 * @throws DI.DependencyLoopException If the value construction triggered a dependency loop.
 */
inline fun <A, reified T : Any> DIAware.instanceOrNull(tag: Any? = null, arg: Typed<A>) = InstanceOrNull<A, T>(arg.type, org.kodein.type.generic(), tag, { arg.value })

/**
 * Gets an instance of [T] for the given type and tag, curried from a factory that takes an argument [A], or null if none is found.
 *
 * A & T generics will be preserved!
 *
 * @param A The type of argument the curried factory takes.
 * @param T The type of object to retrieve.
 * @param tag The bound tag, if any.
 * @param fArg A function that returns the argument that will be given to the factory when curried.
 * @return An instance of [T], or null if no factory was found.
 * @throws DI.DependencyLoopException If the value construction triggered a dependency loop.
 */
inline fun <reified A : Any, reified T : Any> DIAware.instanceOrNull(tag: Any? = null, noinline fArg: () -> A) = InstanceOrNull<A, T>(org.kodein.type.generic(), org.kodein.type.generic(), tag, fArg)

/**
 * Gets all instances from providers that match the the given return type and tag.
 *
 * T generics will be preserved!
 *
 * @param T The type of object to retrieve with the providers.
 * @param tag The bound tag, if any.
 * @return A list of [T] instances.
 * @throws DI.DependencyLoopException When calling the factory, if the value construction triggered a dependency loop.
 */
inline fun <reified T : Any> DIAware.allInstances(tag: Any? = null) = AllInstances<T>(org.kodein.type.generic(), tag)

/**
 * Gets all instances from providers that match the the given return type and tag, curried from factories that take an argument [A].
 *
 * A & T generics will be preserved!
 *
 * @param A The type of argument the curried factories take.
 * @param T The type of object to retrieve with the providers.
 * @param tag The bound tag, if any.
 * @param arg The argument that will be given to the factory when curried.
 * @return A list of [T] instances.
 * @throws DI.DependencyLoopException When calling the factory, if the value construction triggered a dependency loop.
 */
inline fun <reified A : Any, reified T : Any> DIAware.allInstances(tag: Any? = null, arg: A) = AllInstances<A, T>(org.kodein.type.generic(), org.kodein.type.generic(), tag, { arg })

/**
 * Gets all instances from providers that match the the given return type and tag, curried from factories that take an argument [A].
 *
 * The argument type is extracted from the `Typed.type` of the argument.
 *
 * A & T generics will be preserved!
 *
 * @param A The type of argument the curried factories take.
 * @param T The type of object to retrieve with the providers.
 * @param tag The bound tag, if any.
 * @param arg The argument that will be given to the factory when curried.
 * @return A list of [T] instances.
 * @throws DI.DependencyLoopException When calling the factory, if the value construction triggered a dependency loop.
 */
inline fun <A, reified T : Any> DIAware.allInstances(tag: Any? = null, arg: Typed<A>) = AllInstances<A, T>(arg.type, org.kodein.type.generic(), tag, { arg.value })

/**
 * Gets all instances from providers that match the the given return type and tag, curried from factories that take an argument [A].
 *
 * A & T generics will be preserved!
 *
 * @param A The type of argument the curried factories take.
 * @param T The type of object to retrieve with the providers.
 * @param tag The bound tag, if any.
 * @param fArg A function that returns the argument that will be given to the factory when curried.
 * @return A list of [T] instances.
 * @throws DI.DependencyLoopException When calling the factory, if the value construction triggered a dependency loop.
 */
inline fun <reified A : Any, reified T : Any> DIAware.allInstances(tag: Any? = null, noinline fArg: () -> A) = AllInstances<A, T>(org.kodein.type.generic(), org.kodein.type.generic(), tag, fArg)

/**
 * Defines a context and its type to be used by DI.
 */
@Deprecated(DEPRECATED_KODEIN_7X, ReplaceWith("diContext(context)"), DeprecationLevel.ERROR)
inline fun <reified C : Any> kcontext(context: C) = DIContext(org.kodein.type.generic(), context)
inline fun <reified C : Any> diContext(context: C) = DIContext(org.kodein.type.generic(), context)

/**
 * Defines a context and its type to be used by DI.
 */
@Deprecated(DEPRECATED_KODEIN_7X, ReplaceWith("diContext(getContext)"), DeprecationLevel.ERROR)
inline fun <reified C : Any> kcontext(crossinline getContext: () -> C) = DIContext<C>(org.kodein.type.generic()) { getContext() }
inline fun <reified C : Any> diContext(crossinline getContext: () -> C) = DIContext<C>(org.kodein.type.generic()) { getContext() }

/**
 * Allows to create a new DI object with a context and/or a trigger set.
 *
 * @param context The new context of the new DI.
 * @param trigger The new trigger of the new DI.
 * @return A DI object that uses the same container as this one, but with its context and/or trigger changed.
 */
inline fun <reified C : Any> DIAware.on(context: C, trigger: DITrigger? = this.diTrigger) = On(diContext(context), trigger)

/**
 * Allows to create a new DI object with a context and/or a trigger set.
 *
 * @param getContext A function that gets the new context of the new DI.
 * @param trigger The new trigger of the new DI.
 * @return A DI object that uses the same container as this one, but with its context and/or trigger changed.
 */
inline fun <reified C : Any> DIAware.on(trigger: DITrigger? = this.diTrigger, crossinline getContext: () -> C) = On(diContext(getContext), trigger)

/**
 * Allows to create a new DI object with a trigger set.
 *
 * @param trigger The new trigger of the new DI.
 * @return A DI object that uses the same container as this one, but with its context and/or trigger changed.
 */
fun DIAware.on(trigger: DITrigger?) = On(diContext, trigger)
