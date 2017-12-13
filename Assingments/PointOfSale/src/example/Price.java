package example;

public class Price /* extends Object */ {
	private int myDollars, myCents;
	
	public Price(int dollars, int cents) {
		this.myDollars = dollars;
		this.myCents = cents;
	}
	
	public int getDollars() {
		return myDollars;
	}

	public int getCents() {
		return myCents;
	}

	public void increaseBy(Price other)
	{
		this.myDollars += other.getDollars();
		this.myCents += other.getCents();
		if (this.myCents >= 100) {
			this.myCents -= 100;
			this.myDollars += 1;
		}
	}
	
	public Price addTo(Price other)
	{
		int sumDollars = this.myDollars, sumCents = this.myCents;
		sumDollars += other.getDollars();
		sumCents += other.getCents();
		if (sumCents >= 100) {
			sumCents -= 100;
			sumDollars += 1;
		}
		return new Price(sumDollars, sumCents);
	}
	
	public Price multiplyBy(int num) {
		int resultDollars, resultCents;
		resultDollars = myDollars * num;
		resultCents  = myCents * num;
		resultDollars += (resultCents / 100);
		resultCents %= 100;
		return new Price(resultDollars, resultCents);
	}

	@Override
	public String toString() {
		return "$" + myDollars + "." + String.format("%02d", myCents);
	}
}
