package com.ssparrow.projecteuler;

import static org.junit.Assert.*;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class Problem1To25Test {

	@Test
	public void testP001Find3or5MultipleSum() {
		assertEquals(0, Problem1To25.p001Find3or5MultipleSum(0));

		assertEquals(0, Problem1To25.p001Find3or5MultipleSum(1));

		assertEquals(0, Problem1To25.p001Find3or5MultipleSum(2));

		assertEquals(0, Problem1To25.p001Find3or5MultipleSum(3));

		assertEquals(3, Problem1To25.p001Find3or5MultipleSum(4));

		assertEquals(8, Problem1To25.p001Find3or5MultipleSum(6));

		assertEquals(23, Problem1To25.p001Find3or5MultipleSum(10));
		
		//3 5 6 9 10 12 15 18
		assertEquals(78, Problem1To25.p001Find3or5MultipleSum(20));
		
		assertEquals(233168, Problem1To25.p001Find3or5MultipleSum(1000));
	}
	
	
	@Test
	public void testP002FindEvenFibonacciNumberSum(){
		assertEquals(0, Problem1To25.p002FindEvenFibonacciNumberSum(0));

		assertEquals(0, Problem1To25.p002FindEvenFibonacciNumberSum(1));

		assertEquals(2, Problem1To25.p002FindEvenFibonacciNumberSum(2));

		assertEquals(2, Problem1To25.p002FindEvenFibonacciNumberSum(3));

		assertEquals(2, Problem1To25.p002FindEvenFibonacciNumberSum(5));

		assertEquals(10, Problem1To25.p002FindEvenFibonacciNumberSum(10));

		assertEquals(44, Problem1To25.p002FindEvenFibonacciNumberSum(100));

		assertEquals(4613732, Problem1To25.p002FindEvenFibonacciNumberSum(4000000));
	}
	
	
	@Test
	public void testP003FindLargestPrimeFactor(){
		assertEquals(BigInteger.valueOf(1), Problem1To25.p003FindLargestPrimeFactor(BigInteger.valueOf(1)));

		assertEquals(BigInteger.valueOf(2), Problem1To25.p003FindLargestPrimeFactor(BigInteger.valueOf(2)));

		assertEquals(BigInteger.valueOf(3), Problem1To25.p003FindLargestPrimeFactor(BigInteger.valueOf(3)));

		assertEquals(BigInteger.valueOf(2), Problem1To25.p003FindLargestPrimeFactor(BigInteger.valueOf(4)));

		assertEquals(BigInteger.valueOf(5), Problem1To25.p003FindLargestPrimeFactor(BigInteger.valueOf(5)));

		assertEquals(BigInteger.valueOf(5), Problem1To25.p003FindLargestPrimeFactor(BigInteger.valueOf(10)));

		assertEquals(BigInteger.valueOf(5), Problem1To25.p003FindLargestPrimeFactor(BigInteger.valueOf(15)));

		assertEquals(BigInteger.valueOf(2), Problem1To25.p003FindLargestPrimeFactor(BigInteger.valueOf(16)));

		assertEquals(BigInteger.valueOf(17), Problem1To25.p003FindLargestPrimeFactor(BigInteger.valueOf(17)));

		assertEquals(BigInteger.valueOf(5), Problem1To25.p003FindLargestPrimeFactor(BigInteger.valueOf(20)));
		
		assertEquals(BigInteger.valueOf(11), Problem1To25.p003FindLargestPrimeFactor(BigInteger.valueOf(22)));

		assertEquals(BigInteger.valueOf(6857), Problem1To25.p003FindLargestPrimeFactor(new BigInteger("600851475143",10)));
	}

	@Test
	public void testP004FindLargestPalindromicNumber(){
		assertTrue(Problem1To25.isPalindrome(101));

		assertTrue(Problem1To25.isPalindrome(111));

		assertTrue(Problem1To25.isPalindrome(1001));

		assertFalse(Problem1To25.isPalindrome(1011));
		
		assertTrue(Problem1To25.isPalindrome(11211));
		
		assertEquals(906609, Problem1To25.p004FindLargestPalindromicNumber());
	}
	
	@Test
	public void testP005FindSmallestDivisibleBy1ToN(){
		
		assertEquals(1, Problem1To25.p005FindSmallestDivisibleBy1ToN(1));
		
		assertEquals(2, Problem1To25.p005FindSmallestDivisibleBy1ToN(2));

		assertEquals(6, Problem1To25.p005FindSmallestDivisibleBy1ToN(3));
		
		assertEquals(2520, Problem1To25.p005FindSmallestDivisibleBy1ToN(10));
		
		assertEquals(232792560, Problem1To25.p005FindSmallestDivisibleBy1ToN(20));
	}
	
	@Test
	public void testP006FindDiffSumSquareAndSquareSum(){
		assertEquals(0, Problem1To25.p006FindDiffSumSquareAndSquareSum(1));
		
		assertEquals(4, Problem1To25.p006FindDiffSumSquareAndSquareSum(2));

		assertEquals(2640, Problem1To25.p006FindDiffSumSquareAndSquareSum(10));

		assertEquals(25164150, Problem1To25.p006FindDiffSumSquareAndSquareSum(100));
	}
	
	@Test
	public void testP007FindNthPrimeNumber(){
		assertEquals(2, Problem1To25.p007FindNthPrimeNumber(1));
		
		assertEquals(3, Problem1To25.p007FindNthPrimeNumber(2));
		
		assertEquals(5, Problem1To25.p007FindNthPrimeNumber(3));
		
		assertEquals(7, Problem1To25.p007FindNthPrimeNumber(4));
		
		assertEquals(11, Problem1To25.p007FindNthPrimeNumber(5));
		
		assertEquals(104743, Problem1To25.p007FindNthPrimeNumber(10001));
	}
	
	@Test
	public void testP008FindLargest5DigitProduct(){
		String input="73167176531330624919225119674426574742355349194934"+
				"96983520312774506326239578318016984801869478851843"+
				"85861560789112949495459501737958331952853208805511"+
				"12540698747158523863050715693290963295227443043557"+
				"66896648950445244523161731856403098711121722383113"+
				"62229893423380308135336276614282806444486645238749"+
				"30358907296290491560440772390713810515859307960866"+
				"70172427121883998797908792274921901699720888093776"+
				"65727333001053367881220235421809751254540594752243"+
				"52584907711670556013604839586446706324415722155397"+
				"53697817977846174064955149290862569321978468622482"+
				"83972241375657056057490261407972968652414535100474"+
				"82166370484403199890008895243450658541227588666881"+
				"16427171479924442928230863465674813919123162824586"+
				"17866458359124566529476545682848912883142607690042"+
				"24219022671055626321111109370544217506941658960408"+
				"07198403850962455444362981230987879927244284909188"+
				"84580156166097919133875499200524063689912560717606"+
				"05886116467109405077541002256983155200055935729725"+
				"71636269561882670428252483600823257530420752963450";
		assertEquals(40824, Problem1To25.p008FindLargest5DigitProduct(input));
	}
	
	@Test
	public void testP009FindTheLargestPythagorean(){
		assertEquals(new Triplet(3,4,5), Problem1To25.p009FindTheLargestPythagorean(12));
		

		assertEquals(new Triplet(200,375,425), Problem1To25.p009FindTheLargestPythagorean(1000));
	}
	
	@Test
	public void testP010FindProductOfPrimeBelowN(){
		assertEquals(BigInteger.valueOf(2), Problem1To25.p010FindSumOfPrimeBelowN(2));

		assertEquals(BigInteger.valueOf(5), Problem1To25.p010FindSumOfPrimeBelowN(3));
		
		assertEquals(BigInteger.valueOf(5), Problem1To25.p010FindSumOfPrimeBelowN(4));

		assertEquals(BigInteger.valueOf(10), Problem1To25.p010FindSumOfPrimeBelowN(5));

		assertEquals(BigInteger.valueOf(77), Problem1To25.p010FindSumOfPrimeBelowN(20));
		
		assertEquals(BigInteger.valueOf(21171191), Problem1To25.p010FindSumOfPrimeBelowN(20000));

		assertEquals(new BigInteger("142913828922",10), Problem1To25.p010FindSumOfPrimeBelowN(2000000));
	}
	
	@Test
	public void testP011FindLargestAdjacentProduct(){
		int [][] matrix=new int[][]{{8,2,22,97,38,15,0,40,0,75,4,5,7,78,52,12,50,77,91,8},
				{49,49,99,40,17,81,18,57,60,87,17,40,98,43,69,48,4,56,62,0},
				{81,49,31,73,55,79,14,29,93,71,40,67,53,88,30,3,49,13,36,65},
				{52,70,95,23,4,60,11,42,69,24,68,56,1,32,56,71,37,2,36,91},
				{22,31,16,71,51,67,63,89,41,92,36,54,22,40,40,28,66,33,13,80},
				{24,47,32,60,99,3,45,2,44,75,33,53,78,36,84,20,35,17,12,50},
				{32,98,81,28,64,23,67,10,26,38,40,67,59,54,70,66,18,38,64,70},
				{67,26,20,68,2,62,12,20,95,63,94,39,63,8,40,91,66,49,94,21},
				{24,55,58,5,66,73,99,26,97,17,78,78,96,83,14,88,34,89,63,72},
				{21,36,23,9,75,0,76,44,20,45,35,14,0,61,33,97,34,31,33,95},
				{78,17,53,28,22,75,31,67,15,94,3,80,4,62,16,14,9,53,56,92},
				{16,39,5,42,96,35,31,47,55,58,88,24,0,17,54,24,36,29,85,57},
				{86,56,0,48,35,71,89,7,5,44,44,37,44,60,21,58,51,54,17,58},
				{19,80,81,68,5,94,47,69,28,73,92,13,86,52,17,77,4,89,55,40},
				{4,52,8,83,97,35,99,16,7,97,57,32,16,26,26,79,33,27,98,66},
				{88,36,68,87,57,62,20,72,3,46,33,67,46,55,12,32,63,93,53,69},
				{4,42,16,73,38,25,39,11,24,94,72,18,8,46,29,32,40,62,76,36},
				{20,69,36,41,72,30,23,88,34,62,99,69,82,67,59,85,74,4,36,16},
				{20,73,35,29,78,31,90,1,74,31,49,71,48,86,81,16,23,57,5,54},
				{1,70,54,71,83,51,54,69,16,92,33,48,61,43,52,1,89,19,67,48}};
		
		assertEquals(70600674, Problem1To25.p011FindLargestAdjacentProduct(matrix));
	}
	
	@Test
	public void testGetAllPrimeBelowN(){
		assertArrayEquals(new int[]{1}, PrimeUtil.getAllPrimeBelowN(1));

		assertArrayEquals(new int[]{1,2}, PrimeUtil.getAllPrimeBelowN(2));

		assertArrayEquals(new int[]{1,2,3}, PrimeUtil.getAllPrimeBelowN(3));

		assertArrayEquals(new int[]{1,2,3}, PrimeUtil.getAllPrimeBelowN(4));

		assertArrayEquals(new int[]{1,2,3,5}, PrimeUtil.getAllPrimeBelowN(5));
		
		assertArrayEquals(new int[]{1,2,3,5,7}, PrimeUtil.getAllPrimeBelowN(10));

		assertArrayEquals(new int[]{1,2,3,5,7,11,13,17,19}, PrimeUtil.getAllPrimeBelowN(20));
		

		assertEquals(5134, PrimeUtil.getAllPrimeBelowN(50000).length);
	}
	
	@Test
	public void testP012GetTriangleNumberWithGivenDivisors(){
		assertEquals(76576500, Problem1To25.p012GetTriangleNumberWithGivenDivisors(500));
	}
	
	@Test
	public void testP013FindFirst10DigitOfSum(){
		String [] input = new String []{"37107287533902102798797998220837590246510135740250",
										"46376937677490009712648124896970078050417018260538",
										"74324986199524741059474233309513058123726617309629",
										"91942213363574161572522430563301811072406154908250",
										"23067588207539346171171980310421047513778063246676",
										"89261670696623633820136378418383684178734361726757",
										"28112879812849979408065481931592621691275889832738",
										"44274228917432520321923589422876796487670272189318",
										"47451445736001306439091167216856844588711603153276",
										"70386486105843025439939619828917593665686757934951",
										"62176457141856560629502157223196586755079324193331",
										"64906352462741904929101432445813822663347944758178",
										"92575867718337217661963751590579239728245598838407",
										"58203565325359399008402633568948830189458628227828",
										"80181199384826282014278194139940567587151170094390",
										"35398664372827112653829987240784473053190104293586",
										"86515506006295864861532075273371959191420517255829",
										"71693888707715466499115593487603532921714970056938",
										"54370070576826684624621495650076471787294438377604",
										"53282654108756828443191190634694037855217779295145",
										"36123272525000296071075082563815656710885258350721",
										"45876576172410976447339110607218265236877223636045",
										"17423706905851860660448207621209813287860733969412",
										"81142660418086830619328460811191061556940512689692",
										"51934325451728388641918047049293215058642563049483",
										"62467221648435076201727918039944693004732956340691",
										"15732444386908125794514089057706229429197107928209",
										"55037687525678773091862540744969844508330393682126",
										"18336384825330154686196124348767681297534375946515",
										"80386287592878490201521685554828717201219257766954",
										"78182833757993103614740356856449095527097864797581",
										"16726320100436897842553539920931837441497806860984",
										"48403098129077791799088218795327364475675590848030",
										"87086987551392711854517078544161852424320693150332",
										"59959406895756536782107074926966537676326235447210",
										"69793950679652694742597709739166693763042633987085",
										"41052684708299085211399427365734116182760315001271",
										"65378607361501080857009149939512557028198746004375",
										"35829035317434717326932123578154982629742552737307",
										"94953759765105305946966067683156574377167401875275",
										"88902802571733229619176668713819931811048770190271",
										"25267680276078003013678680992525463401061632866526",
										"36270218540497705585629946580636237993140746255962",
										"24074486908231174977792365466257246923322810917141",
										"91430288197103288597806669760892938638285025333403",
										"34413065578016127815921815005561868836468420090470",
										"23053081172816430487623791969842487255036638784583",
										"11487696932154902810424020138335124462181441773470",
										"63783299490636259666498587618221225225512486764533",
										"67720186971698544312419572409913959008952310058822",
										"95548255300263520781532296796249481641953868218774",
										"76085327132285723110424803456124867697064507995236",
										"37774242535411291684276865538926205024910326572967",
										"23701913275725675285653248258265463092207058596522",
										"29798860272258331913126375147341994889534765745501",
										"18495701454879288984856827726077713721403798879715",
										"38298203783031473527721580348144513491373226651381",
										"34829543829199918180278916522431027392251122869539",
										"40957953066405232632538044100059654939159879593635",
										"29746152185502371307642255121183693803580388584903",
										"41698116222072977186158236678424689157993532961922",
										"62467957194401269043877107275048102390895523597457",
										"23189706772547915061505504953922979530901129967519",
										"86188088225875314529584099251203829009407770775672",
										"11306739708304724483816533873502340845647058077308",
										"82959174767140363198008187129011875491310547126581",
										"97623331044818386269515456334926366572897563400500",
										"42846280183517070527831839425882145521227251250327",
										"55121603546981200581762165212827652751691296897789",
										"32238195734329339946437501907836945765883352399886",
										"75506164965184775180738168837861091527357929701337",
										"62177842752192623401942399639168044983993173312731",
										"32924185707147349566916674687634660915035914677504",
										"99518671430235219628894890102423325116913619626622",
										"73267460800591547471830798392868535206946944540724",
										"76841822524674417161514036427982273348055556214818",
										"97142617910342598647204516893989422179826088076852",
										"87783646182799346313767754307809363333018982642090",
										"10848802521674670883215120185883543223812876952786",
										"71329612474782464538636993009049310363619763878039",
										"62184073572399794223406235393808339651327408011116",
										"66627891981488087797941876876144230030984490851411",
										"60661826293682836764744779239180335110989069790714",
										"85786944089552990653640447425576083659976645795096",
										"66024396409905389607120198219976047599490197230297",
										"64913982680032973156037120041377903785566085089252",
										"16730939319872750275468906903707539413042652315011",
										"94809377245048795150954100921645863754710598436791",
										"78639167021187492431995700641917969777599028300699",
										"15368713711936614952811305876380278410754449733078",
										"40789923115535562561142322423255033685442488917353",
										"44889911501440648020369068063960672322193204149535",
										"41503128880339536053299340368006977710650566631954",
										"81234880673210146739058568557934581403627822703280",
										"82616570773948327592232845941706525094512325230608",
										"22918802058777319719839450180888072429661980811197",
										"77158542502016545090413245809786882778948721859617",
										"72107838435069186155435662884062257473692284509516",
										"20849603980134001723930671666823555245252804609722",
										"53503534226472524250874054075591789781264330331690"};
		int [] expected = new int[]{5, 5, 3, 7, 3, 7, 6, 2, 3, 0};
		assertArrayEquals(expected, Problem1To25.p013FindFirst10DigitOfSum(input));
	}
	
	@Test
	public void testP014findLongestCollatzChain(){
		assertEquals(3, Problem1To25.p014findLongestCollatzChain(5));

		assertEquals(871, Problem1To25.p014findLongestCollatzChain(1000));
		
		assertEquals(77031, Problem1To25.p014findLongestCollatzChain(100000));

		assertEquals(837799, Problem1To25.p014findLongestCollatzChain(1000000));
	}
	
	@Test
	public void testP015CalculateRouteNumberInMatrix(){
		BigInteger [][] result=new BigInteger[3][3];
		assertEquals(new BigInteger("6",10), Problem1To25.p015CalculateRouteNumberInMatrix(result, 0, 0, 2));
		
		result=new BigInteger[11][11];
		assertEquals(new BigInteger("184756",10), Problem1To25.p015CalculateRouteNumberInMatrix(result, 0, 0, 10));
		
		result=new BigInteger[16][16];
		assertEquals(new BigInteger("155117520",10) , Problem1To25.p015CalculateRouteNumberInMatrix(result, 0, 0, 15));

		result=new BigInteger[21][21];
		assertEquals(new BigInteger("137846528820",10), Problem1To25.p015CalculateRouteNumberInMatrix(result, 0, 0, 20));
	}
	
	@Test
	public void testP016GetSumOfDigitsFor2Exp(){
		assertEquals(new BigInteger("26", 10), Problem1To25.p016GetSumOfDigitsFor2Exp(15));

		assertEquals(new BigInteger("115", 10), Problem1To25.p016GetSumOfDigitsFor2Exp(100));

		assertEquals(new BigInteger("1366", 10), Problem1To25.p016GetSumOfDigitsFor2Exp(1000));
	}
	
	@Test
	public void testP017GetTotalLetterNumber(){
		assertEquals(4, Problem1To25.getStringRepresentationLength(5));

		assertEquals(9, Problem1To25.getStringRepresentationLength(17));

		assertEquals(10, Problem1To25.getStringRepresentationLength(68));
		
		assertEquals(20, Problem1To25.getStringRepresentationLength(115));
		
		assertEquals(23, Problem1To25.getStringRepresentationLength(342));
		
		assertEquals(21124, Problem1To25.p017GetTotalLetterNumber(1000));
	}
	
	@Test
	public void testP018FindMaxPathInMatrix(){
		int [][] matrix = new int [][] {{3},
										{7, 4},
										{2, 4, 6},
										{8, 5, 9, 3}};
		int [] path = new int [matrix.length];
		assertEquals(23, Problem1To25.p018FindMaxPathInMatrix(matrix, 0, 0, path));
		
		
		matrix = new int [][]{{75},
							{95, 64},
							{17, 47, 82},
							{18, 35, 87, 10},
							{20, 4, 82, 47, 65},
							{19, 1, 23, 75, 3, 34},
							{88, 2, 77, 73, 7, 63, 67},
							{99, 65, 4, 28, 6, 16, 70, 92},
							{41, 41, 26, 56, 83, 40, 80, 70, 33},
							{41, 48, 72, 33, 47, 32, 37, 16, 94, 29},
							{53, 71, 44, 65, 25, 43, 91, 52, 97, 51, 14},
							{70, 11, 33, 28, 77, 73, 17, 78, 39, 68, 17, 57},
							{91, 71, 52, 38, 17, 14, 91, 43, 58, 50, 27, 29, 48},
							{63, 66, 4, 68, 89, 53, 67, 30, 73, 16, 69, 87, 40, 31},
							{4, 62, 98, 27, 23, 9, 70, 98, 73, 93, 38, 53, 60, 4, 23}};
		path = new int [matrix.length];
		assertEquals(1074, Problem1To25.p018FindMaxPathInMatrix(matrix, 0, 0, path));
	}	
	
	@Test
	public void testP019FindSundayOnFirstDay(){
		assertEquals(171, Problem1To25.p019FindSundayOnFirstDay(1901, 2000));
	}
	
	@Test
	public void testP020FindSumOfDigitsInNto1(){
		assertEquals(3, Problem1To25.p020FindSumOfDigitsInNto1(5));

		assertEquals( 27, Problem1To25.p020FindSumOfDigitsInNto1(10));

		assertEquals( 54, Problem1To25.p020FindSumOfDigitsInNto1(20));

		assertEquals( 117, Problem1To25.p020FindSumOfDigitsInNto1(30));

		assertEquals( 648, Problem1To25.p020FindSumOfDigitsInNto1(100));
	}
	
	@Test
	public void testP021FindAllAmicableNumbers(){
		Set<AmicablePair> allAmicableNumbers = Problem1To25.p021FindAllAmicableNumbers(300);
		
		assertEquals(1, allAmicableNumbers.size());
		
		allAmicableNumbers = Problem1To25.p021FindAllAmicableNumbers(10000);
		
		int sum=0;
		for(AmicablePair pair:allAmicableNumbers){
			sum+=pair.getA()+pair.getB();
			System.out.println(pair.getA()+":"+pair.getB());
		}
		assertEquals(31626, sum);
			
	}
	
	@Test
	public void testP022CalculateNameScore() throws IOException{
		assertEquals(BigInteger.valueOf(871198282),Problem1To25.p022CalculateNameScore("test/data/p022.txt"));
	}
	
	@Test
	public void testP023FindSumOfAllNumberNotFormedByAbundantNumbers(){
		assertEquals(BigInteger.valueOf(4179871), Problem1To25.p023FindSumOfAllNumberNotFormedByAbundantNumbers());
	}
	
	@Test
	public void testP024FindGivenPermutation(){
		assertEquals("102", Problem1To25.p024FindGivenPermutation("012", 3));

		assertEquals("0312", Problem1To25.p024FindGivenPermutation("0123", 5));

		assertEquals("2783915460", Problem1To25.p024FindGivenPermutation("0123456789", 1000000));
	}
	
	@Test
	public void testP025FindFirstFibonacciNumberWithGivenDigits(){
		assertEquals(12, Problem1To25.p025FindFirstFibonacciNumberWithGivenDigits(3));

		assertEquals(476, Problem1To25.p025FindFirstFibonacciNumberWithGivenDigits(100));
		
		assertEquals(4782, Problem1To25.p025FindFirstFibonacciNumberWithGivenDigits(1000));
	}
	
}
