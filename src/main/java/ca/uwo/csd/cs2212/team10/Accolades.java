package ca.uwo.csd.cs2212.team10;
/**
 * This class will be used to keep track of the accolade badges achieved by the user 
 * @author Neeraja Murali Dharan 
 */

public class Accolades {
	
    //Array of accolades consisting of 20 Accolade_Node objects 
	private Accolade_Node[]accolades=new Accolade_Node[20];
	
	/**
	 * Accolades constructor 
	 */
	public Accolades(){
		for (int i=0; i<accolades.length;i++){
			accolades[i]=new Accolade_Node(); 
		}
	}
	
	/**
	 * set_accolades method adds descriptions and titles to elements in array of constructor and checks if they have been achieved
	 * @param unit Integer where 0 is stored if the units are in metric and 1 if the units are in imperial
	 * @param bestlife BestLifeStats which is an object containing statistics for Best Life from the user's FitBit
	 * @param daily DailyStats which is an object containing daily statistics from the user's FitBit 
	 * @param heartrate HeartStats which is an object containing heart rate statistics from the user's FitBit
	 */
	public void set_accolades(int unit, BestLifeStats bestlife,DailyStats daily,HeartStats heartrate){
		accolades[0].setTitle("You Beat It");
		accolades[0].setDescription("Your cardio heart rate just surpassed Michael Jackson's Beat It(148 BPM).So beat it, just beat it!");
		if (heartrate.getCardio()>148){
			accolades[0].setCheck(true);
		}
		
		accolades[1].setTitle("Fitness God");
		accolades[1].setDescription("Congratulations! Your peak heart rate is faster than Eminem's Rap God (148BPM). Thats quite an achievement");
		
		//User achieves accolade if peak heart rate is faster than 148(BPM) 
		if (heartrate.getPeak()>148){
			accolades[1].setCheck(true);
		}
		
		accolades[2].setTitle("Live La Vida Loca");
		accolades[2].setDescription("Even Ricky Martin guessed it! Your peak heart rate is higher than 178 BPM, faster than Live La Vida Loca. Come on!");
		
		//User achieves accolade if peak heart rate is faster than 178(BPM) 
		if (heartrate.getPeak()>10){
			accolades[2].setCheck(true);
		}
		
		accolades[3].setTitle("Half A Marathon");
		accolades[3].setDescription("You just ran 21 kilometers, or half a marathon. You're so fit, you make hearts beat faster just watching you go!");
		
		/*
		 * metric(kilometers)
		 * User achieves accolade if life distance is greater than 21(KM)
		*/ 
		if (unit==0){
			if (bestlife.getLifeDistance()>21){
				accolades[3].setCheck(true);
			}
		}
		/*
		 * imperial(miles)
		 * User achieves accolade if life distance is greater than 13(MILES)
		*/ 
		else{
			if (bestlife.getLifeDistance()>13){
				accolades[3].setCheck(true);
			}
		}

		accolades[4].setTitle("Marathon Completed");
		accolades[4].setDescription("You just ran a marathon! You may now cross an imaginary finish line in your head!");
		
		/*
		 * metric(kilometers)
		 * User achieves accolade if life distance is greater than 42(KM)
		*/ 
		if (unit==0){
			if (bestlife.getLifeDistance()>42){
				accolades[4].setCheck(true);
			}
		}
		/*
		 * imperial(miles)
		 * User achieves accolade if life distance is greater than 26(MILES)
		*/ 
		else{
			if (bestlife.getLifeDistance()>26){
				accolades[4].setCheck(true);
			}
		}

		accolades[5].setTitle("Cookie Monster");
		accolades[5].setDescription("You burned off as many calories as 40 cookies. You are one cookie that won't crumble!");
		
		//User achieves accolade if more than 2000 calories burned
		if (daily.getCalories()>=2000){
			accolades[5].setCheck(true);
		}
		
		accolades[6].setTitle("Great Wall Climbed");
		accolades[6].setDescription("You have virtually climbed the Great Wall of China by taking greater than 5268 steps, in one day. Gongxi! ");
		
		//User achieves accolade if greater than 5268 steps are taken in a day
		if (daily.getSteps()>=5268){
			accolades[6].setCheck(true);
		}
		
		accolades[7].setTitle("Laugh & Snicker");
		accolades[7].setDescription("You're definitely you when you're fit! You just burned off calories equivalent to 15 Snickers bar!");
		
		//User achieves accolade if more than 5000 calories are burned 
		if (daily.getCalories()>=5000){
			accolades[7].setCheck(true);
		}
		
		
		accolades[8].setTitle("Can't Touch This");
		accolades[8].setDescription("You're so high up, even MC Hammer can't touch this. Your best floor count is higher than 15");
		
		//User achieves accolade if their best floor count is greater than 20 
		if (bestlife.getBestFloor()>=20){
			accolades[8].setCheck(true);
		}
		
		accolades[9].setTitle("Moves Like Jaguar");
		accolades[9].setDescription("You've got moves! Your fairly active minutes is higher than 120. Roar on!");
		
		//User achieves accolade if their very active minutes is greater than 120 on a given day
		if (daily.getFairlyActiveMins()>=120){
			accolades[9].setCheck(true);
		}
		
		accolades[10].setTitle("Text Champ");
		accolades[10].setDescription("You just burned off 500 calories, that is equivalent to almost 13 straight hours of texting, keep it going!");
		
		//User achieves accolade if their daily calories burned is higher than 500 calories
		if (daily.getCalories()>=500){
			accolades[10].setCheck(true);
		}
		
		accolades[11].setTitle("Laugh Away");
		accolades[11].setDescription("Did you know that laughing burns calories? You just burned off 1000 calories, equivalent of 5 straight hours of laughing, now laugh a little while getting fit!");
		
		//User achieves accolade if daily calories burned is greater than 1000
		if (daily.getCalories()>=1000){
			accolades[11].setCheck(true);
		}
		
		accolades[12].setTitle("Bonjour Paris!");
		accolades[12].setDescription("You just climbed the Eiffel Tower with 1700 steps! Enjoy the view and grab a croissant after?");
		
		//User achieves accolade if best total number of steps is greater than 1700 steps
		if (bestlife.getBestStep()>=1700){
			accolades[12].setCheck(true);
		}
		
		accolades[13].setTitle("You Beat Flash!");
		accolades[13].setDescription("Your light active minute is above average (90 minutes).Flash is jealous!");
		
		//User achieves accolade if Light Active Minutes is greater than 2000
		if (daily.getLightActiveMins()>=90){
			accolades[13].setCheck(true);
		}
		
		accolades[14].setTitle("Space Party Time!");
		accolades[14].setDescription("Are you a star? Because you just made it to space with your step count!");
		
		//User achieves accolade if they have taken over 47714000 steps in their lifetime
		if (bestlife.getLifeSteps()>=477714000){
			accolades[14].setCheck(true);
		}
		
		accolades[15].setTitle("Burn Baby Burn");
		accolades[15].setDescription("Damn!You just burned 6000 calories, thats how much an Olympian burns");
		
		//User achieves accolade if they have burned off more than 6000 calories in a day
		if (daily.getCalories()>=6000){
			accolades[15].setCheck(true);
		}
		
		accolades[16].setTitle("Skydiving-Check");
		accolades[16].setDescription("Check skydiving off your Bucketlist! Your floor count is 1000, that's where you need to be to skydive");
		
		//User achieves accolade if life floors count is greater than 1000 
		if (bestlife.getLifeFloors()>=1000){
			accolades[6].setCheck(true);
		}
		
		accolades[17].setTitle("Big Mac Burner");
		accolades[17].setDescription("You just burned the equivalence of 6 Big Macs,you got this!");
		
		//User achieves accolade if they have burned off more than 3000 calories in a day
		if (daily.getCalories()>=3000){
			accolades[17].setCheck(true);
		}
		
		accolades[18].setTitle("You're A Hero");
		accolades[18].setDescription("You have 120 very active minutes. Now you can make the world a healthier place");
		
		//User achieves accolade if Very Active Minutes is greater than 120
		if (daily.getVeryActiveMins()>=120){
			accolades[18].setCheck(true);
		}
		
		accolades[19].setTitle("Hiker Status Achieved");
		accolades[19].setDescription("With your step count, you have just climbed Mount Everest! Relax and enjoy the view");
		
		//User achieves accolade if total life steps is greater than 58070
		if (bestlife.getLifeSteps()>=58070){
			accolades[19].setCheck(true);
		}
	}
	
	/**
     * Method returns title of an accolade at a specific index in array 
     * @param index integer of the accolade to be accessed 
     * @return String Title
     */
	public String getTitle(int index){
		return accolades[index].getTitle(); 
	}
	/**
     * Method returns description of an accolade at a specific index in array 
     * @param index integer of the accolade to be accessed 
     * @return String Description
     */
	public String getDescription(int index){
		return accolades[index].getDescription();
	}
	/**
     * Method returns check value that indicates if accolade at a specific index in array has been achieved 
     * @param index integer of the accolade to be accessed 
     * @return Boolean check
     */
	public boolean getCheck(int index){
		return accolades[index].getCheck(); 
	}
	
}
