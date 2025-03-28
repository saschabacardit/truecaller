# TruecallerSimple

A simple implementation of the given Truecaller task, while there is an attempt at keeping simplicity, the secondary aim is a 
'mock' or 'simplified' version of a real application that will do full use of things like MVVM, compose, DI, etc...;

The application has to do the following things:
Download an URL content (entire webpage)
Then display on the UI the following
1. The first 15th character of the given string
2. Every 15th character
3. Count the number of unique (case insensitive) words (defined as 'any non-\s character')

Note that every one of these problems has multiple solutions, while performant and LeetCode-esque algorithms and data structure,
might have been preferred I feel that is over-engineering the entire project (which is already so, 
by virtue of using MVVM, Dagger, etc.. in such a small project) and as such while they are in the project,
they're marked as @deprecated.

Note that this isn't a "performant code is bad", but rather that because the required operation isn't that complex,
along with using a frankly slow language (Kotlin, Java), using code that will be harder to understand isn't 
likely a good option as clearly full performance isn't the aim.

Note that methods also have comments & thought explanations.