package v100;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class MinimalCoverage_UVa10020 {

	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = sc.nextInt();
		while(tc-->0)
		{
			int m = sc.nextInt(), n = 0, idx = 0;
			ArrayList<Segment> segs = new ArrayList<Segment>(100000);
			while(true)
			{
				int l = sc.nextInt(), r = sc.nextInt();
				if(l == 0 && r == 0)
					break;
				segs.add(new Segment(l, r));
			}
			
			Collections.sort(segs);
			ArrayList<Segment> sol = new ArrayList<Segment>(segs.size());
			while(n < m)
			{
				int best = -1;
				while(idx < segs.size() && segs.get(idx).L <= n)
				{
					if(segs.get(idx).R > n  && (best == -1 || segs.get(idx).R > segs.get(best).R))
						best = idx;					
					++idx;
				}
				if(best == -1)
					break;
				
				sol.add(segs.get(best));
				n = segs.get(best).R;
				
			}
			if(n < m)
				out.println(0);
			else
			{
				out.println(sol.size());
				for(Segment s: sol)
					out.printf("%d %d\n", s.L, s.R);
			}
			if(tc != 0)
				out.println();
		}
		out.flush();
		out.close();
	}
	
	static class Segment implements Comparable<Segment>
	{
		int L, R;
		
		Segment(int x, int y)
		{
			L = x;
			R = y;
		}
		
		public int compareTo(Segment s)
		{
			if(L != s.L)
				return L - s.L;
			return s.R - R;
		}
	}

	static class Scanner 
	{
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream s){	br = new BufferedReader(new InputStreamReader(s));}

		public String next() throws IOException 
		{
			while (st == null || !st.hasMoreTokens()) 
				st = new StringTokenizer(br.readLine());
			return st.nextToken();
		}

		public int nextInt() throws IOException {return Integer.parseInt(next());}
		
		public long nextLong() throws IOException {return Long.parseLong(next());}

		public String nextLine() throws IOException {return br.readLine();}
		
		public double nextDouble() throws IOException
		{
			String x = next();
			StringBuilder sb = new StringBuilder("0");
			double res = 0, f = 1;
			boolean dec = false, neg = false;
			int start = 0;
			if(x.charAt(0) == '-')
			{
				neg = true;
				start++;
			}
			for(int i = start; i < x.length(); i++)
				if(x.charAt(i) == '.')
				{
					res = Long.parseLong(sb.toString());
					sb = new StringBuilder("0");
					dec = true;
				}
				else
				{
					sb.append(x.charAt(i));
					if(dec)
						f *= 10;
				}
			res += Long.parseLong(sb.toString()) / f;
			return res * (neg?-1:1);
		}
		
		public boolean ready() throws IOException {return br.ready();}


	}
}
