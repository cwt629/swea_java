import java.util.*;
import java.io.*;

class Coord {
	int row;
    int col;
    int minDistance;
    
    public Coord(int row, int col, int minDistance){
    	this.row = row;
        this.col = col;
        this.minDistance = minDistance;
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
		final int DISTANCE_THRESHOLD = 250000; // 이동거리는 최악의 경우에도 이 숫자 이상 되지 않으며, 최단거리 세팅을 위한 상수
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		
		/*
		   여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
		*/

		for(int test_case = 1; test_case <= T; test_case++)
		{
			String input = br.readLine();
            StringTokenizer st = new StringTokenizer(input);
            int N = Integer.parseInt(st.nextToken()), K = Integer.parseInt(st.nextToken());
            // 각 인덱스마다의 좌표값 저장
            ArrayList<Coord>[] spots = (ArrayList<Coord>[])new ArrayList[K];
            for (int i = 0; i < K; i++){
            	spots[i] = new ArrayList<Coord>();
            }
            
            for (int i = 0; i < N; i++){
            	input = br.readLine();
                st = new StringTokenizer(input);
                for (int j = 0; j < N; j++){
                	int num = Integer.parseInt(st.nextToken());
                    int dist = (num == 1)? 0 : DISTANCE_THRESHOLD;
                    spots[num - 1].add(new Coord(i, j, dist));
                }
            }
            
            // 검사: 게임이 가능한지
            boolean ableToPlay = true;
            for (int i = 0; i < K; i++){
            	if (spots[i].size() == 0){
                    ableToPlay = false;
                	break;
                }
            }
            
            int answer = -1;
            if (ableToPlay){
            	// 0 ~ k-1까지 최단거리를 갱신하면서 이동한다
                for (int color = 0; color < K - 1; color++){
                	ArrayList<Coord> currentList = spots[color];
                    ArrayList<Coord> nextList = spots[color + 1];
                    for (Coord current: currentList){
                    	for (Coord next: nextList){
                        	int dist = Math.abs(current.row - next.row) + Math.abs(current.col - next.col);
                            if (current.minDistance + dist < next.minDistance){
                            	next.minDistance = current.minDistance + dist;
                            }
                        }
                    }
                }
                
                // 이제 k-1번의 리스트에서 가장 짧은 거리를 고른다
                ArrayList<Coord> finalList = spots[K - 1];
                answer = finalList.get(0).minDistance;
                for (int i = 1; i < finalList.size(); i++){
                	int dist = finalList.get(i).minDistance;
                    if (answer > dist) answer = dist;
                }
            }
            
            bw.write("#" + test_case + " " + answer);
            bw.newLine();
		}
        bw.flush();
	}
}