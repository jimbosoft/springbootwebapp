package com.example.tutorial;

import java.util.function.Function;

import org.springframework.stereotype.Component;

@Component
public class Java8Tutorial
{

	public void runTest1()
	{
		Comparator<Person> cmpAge = (p1, p2)-> p2.getAge() - p1.getAge();
		Comparator<Person> cmpFirstName = (p1, p2)-> p2.getFirstName().compareTo(p1.getFirstName());

		Function<Person, Integer> f1 = p -> p.getAge();
		Function<Person, String> f2 = p -> p.getFirstName();
		Function<Person, String> f3 = p -> p.getLastName();
		
		Comparator<Person> cmpPersonAge = Comparator.comparing(Person::getAge);
		Comparator<Person> cmpPersonFirst = Comparator.comparing(Person::getFirstName);
		
		Comparator<Person> cmp = cmpPersonAge.thenComparing(cmpPersonFirst);
		
		Comparator<Person> cmpA = Comparator.comparing(Person::getAge)
						.thenComparing(Person::getFirstName);

	}
	public void runTest2()
	{
		Predicate<String> p = s -> s.length() < 20;
		Predicate<String> p2 = s -> s.length() > 5;
		
		boolean b = p.test("Hello");
		System.out.println("Hello is shorted the 20 char: " + b);
		
		Predicate<String> p3 = p.and(p2);
		Predicate<String> p4 = p.or(p2);

		System.out.println("Hello is shorted the 20 char and longer then 5: " + p3.test("Yes"));
		System.out.println("Good Morning is shorted the 20 char: " + p3.test("Good Morning"));
		System.out.println("Good mornign gentlemen is shorted the 20 char: " + p3.test("Good mornign gentlemen"));
		
		System.out.println("Hello is shorted the 20 char and longer then 5: " + p4.test("Yes"));
		System.out.println("Good Morning is shorted the 20 char: " + p4.test("Good Morning"));
		System.out.println("Good mornign gentlemen is shorted the 20 char: " + p4.test("Good mornign gentlemen"));
		
		Predicate<String> p5 = Predicate.isEqualTo("Yes");
		System.out.println("No is yes: " + p5.test("No"));
		System.out.println("Yes is yes: " + p5.test("Yes"));

		Predicate<Integer> p6 = Predicate.isEqualTo(3);
		System.out.println("1 is 3: " + p6.test(1));
		System.out.println("3 is 3: " + p6.test(3));

	}
}
@FunctionalInterface
interface Comparator<T>
{
	public int compare(T t1, T t2);
	
	public static <U> Comparator<U> comparing(Function<U, Comparable> f)
	{
		return (p1, p2) -> f.apply(p1).compareTo(p2);
	}
	
	public default Comparator<T> thenComparing(Comparator<T> cmp)
	{
		return (p1, p2) -> compare(p1, p2) == 0 ? cmp.compare(p1,  p2) : compare(p1, p2);
	}

	public default Comparator<T> thenComparing(Function<T, Comparable> f)
	{
//		Comparator<T> cmp = comparing(f);
//		return thenComparing(comparing(f));
		return thenComparing(comparing(f));
	}
}

class Person
{
	private String firstName;
	private String lastName;
	private int age;
	
	public Person(){}
	
	public Person(String first, String last, int age)
	{
		this.firstName = first;
		this.lastName = last;
		this.age = age;
	}
	public int getAge() { return this.age; }

	public String getFirstName()
	{
		return firstName;
	}

	public String getLastName()
	{
		return lastName;
	}
}

@FunctionalInterface
interface Predicate<T>
{
	public boolean test(T t);
	
	public default Predicate<T> and (Predicate<T> other)
	{
		Predicate<T> p = t -> other.test(t) && this.test(t);
		return p;
	}
	public default Predicate<T> or (Predicate<T> other)
	{
		Predicate<T> p = t -> other.test(t) || this.test(t);
		return p;
	}
	public static <U> Predicate<U> isEqualTo (U other)
	{
		Predicate<U> p = t -> t.equals(other);
		return p;
	}
}