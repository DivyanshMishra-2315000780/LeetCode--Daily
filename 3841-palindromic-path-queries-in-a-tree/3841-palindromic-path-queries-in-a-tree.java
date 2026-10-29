class Solution {
    class sol{
        List<List<Integer>> adj;
        int[] tin,tout,dep,val,bit;
        int [][] par;
        int tim,n,lg;
        void dfs(int u,int p,int d)
        {
            tim += 1;
            tin[u] = tim;
            dep[u] = d;
            par[u][0] = p;
            int i =0 ;
            while(i<adj.get(u).size())
                {
                    int v = adj.get(u).get(i);
                    if(v!=p)
                    {
                        dfs(v,u,d+1);                      
                    }
                    i+=1;
                }
            tout[u] =tim;
        }
        void upd(int i,int x)
        {
            while(i<=n)
                {
                    bit[i] ^= x;
                    i += i & -i;
                }
           }
        void rng(int  l,int r,int x)
        {
            upd(l,x);
            upd(r+1,x);
        }
        int qry(int i)
        {
            int s = 0;
            while (i > 0)
                {
                    s ^= bit[i];
                    i -= i & -i;
                }
            return s;
        }
    }
    int lca(sol o ,int a,int b)
    {
        if(o.dep[a] < o.dep[b])
        {
            int t=  a;
            a = b;
            b = t;       
        }
        int j = o.lg - 1;
        while ( j >= 0)
            {
                if(o.par[a][j] != -1){
                if(o.dep[o.par[a][j]] >= o.dep[b])
                {
                    a = o.par[a][j];
                   
                }
                }
                j -= 1;
            }
    if(a == b)
    {
       return a; 
    }
j = o.lg - 1;
while (j >= 0 )
{
        if(o.par[a][j] != o.par[b][j])
        {
            a = o.par[a][j];
            b = o.par[b][j];
        }
        j -= 1;
}
    
return o.par[a][0];
}   public List<Boolean> palindromePath(int n, int[][] ed, String s, String[] qr) {
        sol o = new sol();
        o.n = n;
        o.lg = 17;
        o.tim = 0;
        o.adj = new ArrayList<>();
        int  i =0 ;
        while (i<n)
            {
                o.adj.add(new ArrayList<>());
                i+=1;
            }
        i = 0;
        while (i< ed.length)
            {
                int  u = ed[i][0];
                int v  = ed[i][1];
                o.adj.get(u).add(v);
                o.adj.get(v).add(u);
                i+=1;
            }
        o.tin = new int[n];
        o.tout = new int[n];
        o.dep = new int[n];
        o.val = new int[n];
        o.bit = new int[n+1];
        o.par = new int[n][o.lg];
        i = 0;
        while (i<n)
            {
                o.val[i] = 1 << (s.charAt(i) - 'a');
                int j =0 ;
                while (j<o.lg)
                    {
                        o.par[i][j] = -1;
                        j += 1;
                    }
                i += 1;
            }
        o.dfs(0,-1,0);
        int j = 1;
        while (j < o.lg)
            {
                i = 0;
                while ( i < n)
                    {
                        if(o.par[i][j-1] != -1)
                        {
                            o.par[i][j] = o.par[o.par[i][j-1]][j-1];
                        }
                        else
                        {
                            o.par[i][j] = -1;
                        }

                        i += 1;
                    }
                j += 1;
            }
        i = 0;
        while(i<n)
            {
                o.rng(o.tin[i],o.tout[i],o.val[i]);
                i+= 1;
            }
        List<Boolean> ans = new ArrayList<>();
        i = 0;
        while (i<qr.length)
            {
                String[] pr = qr[i].split(" ");
                if(pr[0].equals("update"))
                {
                    int u = Integer.parseInt(pr[1]);
                    char c = pr[2].charAt(0);
                    int nm = 1 << (c - 'a');
                    int om = o.val[u];
                    if(nm != om)
                    {
                        int df = nm ^ om;

                        o.rng(o.tin[u],o.tout[u],df);
                        o.val[u] = nm;
                    }
                }
                else
                {
                    int  u = Integer.parseInt(pr[1]);
                    int v = Integer.parseInt(pr[2]);
                    int mu = o.qry(o.tin[u]);
                    int mv = o.qry(o.tin[v]);
                    int lc = lca(o,u,v);
                    int mk = mu ^ mv ^ o.val[lc];
                    if(Integer.bitCount(mk) <= 1)
                    {
                        ans.add(true);
                    }
                    else
                    {
                        ans.add(false);
                    }
                }
                i += 1;
            }
        return ans;
    }
}