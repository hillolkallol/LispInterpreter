# Lisp Interpreter in Java
## Kallol Das
## ID: 900334303

Running environment
===================

Language: Java
JDK version: jdk1.8.0_111
IDE: Netbeans IDE

Running and compiling the project
=================================

* A custom Makefile has been added with this project folder. The makefile can be run from the directory of the project with the command: 'make'

Project Design & Development Information
========================================
Project file has mainly four Java files:
1. LispInterpreterMain
2. LispInterpreterLexer
3. LispInterpreterParser
4. LispInterpreterOperations

Implemented Features:
=====================
Expression		Syntax
-----------		--------
Addition	(+ (+ 2 3) 5)
Subtraction	(- (- 2 3) 5)
Multiplication	(* (* 2 3) 5)
Division	(/ (/ 2 3) 5)
Definition	(define r 10)
Assignment	(set! r2 (* r r))
Variable reference	(r)
Constant literal	(12)
Conditional	(if (> 10 20) (+ 1 1) (+ 3 3))
Quotation	(quote (+ 1 2))
Procedure	(lambda ADD (x y) (+ x y))
Procedure Call	(ADD (8 3))
Pre-defined Procedure: sqrt	(sqrt(x))
Pre-defined Procedure: sin	(sin(x))
Pre-defined Procedure: cos	(cos(x))
Pre-defined Procedure: tan	(tan(x))
Dynamic Scoping	(set! x 10)
				(dynamiclambda dynamic_add (y) (+ x y))
				(dynamiclambda dynamic_call_add (x) (dynamic_add (x)))
				(dynamic_call_add (3))


Output
======

* Please find the output file named as 'results.txt' in the project folder.
The output file can be opened from the directory of the project with the command: vim 'results.txt'

* Please find Full 'Test Cases Outputs.txt' inside project directory to get all test cases output.

Tested Input & Output
=====================
Test Case	Output
---------   ---------
(define r 10)	r
(r)	10
(2)	2
(quote (+ 1 2))	(+ 1 2)
(quote (+ a b))	(+ a b)
(if (> 10 20) (+ 1 1) (+ 3 3))	6
(if (< 10 20) (+ 1 1) (+ 3 3))	2
(define a 3)	a
(define b 2)	b
(if (< a b) (+ 1 1) (+ 3 3))	6
(if (> a b) (+ 1 1) (+ 3 3))	2
(define x 2)	x
(define y 2)	y
(if (< a b) (+ x y) (- x y))	0
(if (> a b) (+ x y) (- x y))	4
(sqrt(4))	2
(sin(90))	0.89399666
(cos(90))	-0.44807361
(tan(90))	-1.99520041
(define x 2)	x
(sqrt(x))	1.41421356
(sin(x))	0.90929742
(cos(x))	-0.41614683
(tan(x))	-2.18503986
(define r 10)	r
(set! r 5)	5
(r)	5
(set! r (* 2 2))	4
(r)	4
(set! r (* r r))	16
(r)	16
(set! r 2)	2
(+ 2 3)	5
(- 3 2)	1
(* 2 3)	6
(/ 6 2)	3
(+ (+ 2 3) 5)	10
(- (- 2 3) 5)	-6
(* (* 2 3) 5)	30
(define a 5)	a
(define b 10)	b
(+ a b)	15
(- b a)	5
(* b a)	50
(/ b a)	2
(define x 2)	x
(define y 4)	y
(define z 8)	z
(+ (+ x y) z)	14
(* (* x y) z)	64
(- (- x y) z)	-10
(lambda ADD (x y) (+ x y))	ADD
(ADD (8 3))	11
(lambda SUB (x y) (- x y))	SUB
(SUB (3 2))	1
(lambda MUL (x y) (* x y))	MUL
(MUL (2 3))	6
(lambda DIV (x y) (/ x y))	DIV
(DIV (6 2))	3
(lambda ADD2 (x y z) (+ (+ x y) z))	ADD2
(ADD2(2 2 2))	6
(lambda MUL2 (x y z) (* (* x y) z))	MUL2
(MUL2(2 2 2))	8
(lambda SUB2 (x y z) (- (- x y) z))	SUB2
;;;; static scoping
(set! x 10)
(lambda static_add (y) (+ x y))
(lambda static_call_add (x) (static_add (x)))
(static_call_add (3))	
10
static_add
static_call_add
13
;;;; dynamic scoping
(set! x 10)
(dynamiclambda dynamic_add (y) (+ x y))
(dynamiclambda dynamic_call_add (x) (dynamic_add (x)))
(dynamic_call_add (3))	
10
dynamic_add
dynamic_call_add
6
(quit)	Bye!
