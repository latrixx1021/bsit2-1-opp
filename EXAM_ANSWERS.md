# LICEO RIDE - Semi-Final Laboratory Exam Answers
Name:Jan Michael Vincent T Gagarra

Section: BSIT 2-1

## Question 1
Why is the list in RideManager typed ArrayList&lt;Ride&gt; and not ArrayList&lt;Jeepney&gt;?
Your answer:The list is typed ArrayList<Ride> because Ride is the parent class, so the list can store different types of rides such as Jeepney, Bus, etc. This makes the program more flexible and supports polymorphism.

## Question 2
In showStudentDiscounts(), why must you check instanceof before the cast?
Your answer:We must check instanceof before casting to make sure the object is actually a StudentDiscount (or the expected type). This prevents a ClassCastException and allows the program to safely perform the cast