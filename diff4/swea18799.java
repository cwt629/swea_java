/* 18799. 평균의 평균 */

import java.util.*;
import java.io.*;
import java.text.DecimalFormat;

class StackData {
	int index;
    int sum;
    int count;
    
    public StackData(int index, int sum, int count){
    	this.index = index;
        this.sum = sum;
        this.count = count;
    }
}

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution
{
	public static void main(String args[]) throws Exception
	{
		/*
		   아래의 메소드 호출은 앞으로 표준 입력(키보드) 대신 input.txt 파일로부터 읽어오겠다는 의미의 코드입니다.
		   여러분이 작성한 코드를 테스트 할 때, 편의를 위해서 input.txt에 입력을 저장한 후,
		   이 코드를 프로그램의 처음 부분에 추가하면 이후 입력을 수행할 때 표준 입력 대신 파일로부터 입력을 받아올 수 있습니다.
		   따라서 테스트를 수행할 때에는 아래 주석을 지우고 이 메소드를 사용하셔도 좋습니다.
		   단, 채점을 위해 코드를 제출하실 때에는 반드시 이 메소드를 지우거나 주석 처리 하셔야 합니다.
		 */
		//System.setIn(new FileInputStream("res/input.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		/*
		   여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
		*/

		for(int test_case = 1; test_case <= T; test_case++)
		{
			int size = Integer.parseInt(br.readLine());
            int[] numbers = new int[size];
            String input = br.readLine();
            StringTokenizer st = new StringTokenizer(input);
            for (int i = 0; i < size; i++)
            {
            	numbers[i] = Integer.parseInt(st.nextToken());
            }
            
            double averageSum = 0;
            int averageCount = 0;
            Stack<StackData> dfsStack = new Stack<>();
            dfsStack.push(new StackData(0, 0, 0));
            while (!dfsStack.isEmpty())
            {
            	StackData current = dfsStack.pop();
                if (current.index >= size)
                {
                    if (current.count > 0)
                    {
                		averageSum += (double)current.sum / (double)current.count;
                        averageCount++;
                    }
                    continue;
                }
                
                // 현재 인덱스를 더한 것과 더하지 않은 것에 대해 탐색 지속
                dfsStack.push(new StackData(current.index + 1, current.sum + numbers[current.index], current.count + 1));
                dfsStack.push(new StackData(current.index + 1, current.sum, current.count));
            }
            double answer = averageSum / (double)averageCount;
            String answerToPrint = new DecimalFormat("0.####################").format(answer);
            bw.write("#" + test_case + " " + answerToPrint);
            bw.newLine();
		}
        bw.flush();
	}
}