class PacificAtlanticWaterFlow
{
    int dirs[][]={{-1,0}/*top*/,{0,1},{1,0}/*down*/,{0,-1}};
    public List<List<Integer>> pacificAtlantic(int[][] heights) 
    {
        List<List<Integer>> ans=new ArrayList<>();
        if(heights==null || heights.length==0 || heights[0].length==0)
            return ans;
        int rows=heights.length;
        int cols=heights[0].length;
        
        boolean paci[][]=new boolean[rows][cols];
        boolean atlan[][]=new boolean[rows][cols];
        
        for(int j=0;j<cols;j++)
        {
            dfs(heights,0,j,Integer.MIN_VALUE,paci);//pacific top
            dfs(heights,rows-1,j,Integer.MIN_VALUE,atlan);//atlanic down
        }
        
        for(int i=0;i<rows;i++)
        {
            dfs(heights,i,0,Integer.MIN_VALUE,paci);//pacific left side
            dfs(heights,i,cols-1,Integer.MIN_VALUE,atlan); //atlan right side
        }
        for(int i=0;i<rows;i++)
        {
            for(int j=0;j<cols;j++)
            {
                if(paci[i][j] && atlan[i][j])
                    ans.add(Arrays.asList(i,j));
            }
        }
        return ans;
    }
    
    
    private void dfs(int[][] heights,int i, int j,int prev, boolean visited[][])
    {
        if(i<0 || j<0 || i>=heights.length || j>=heights[0].length)
            return; //out of bound
        if(visited[i][j])
            return; //already visited
        if(heights[i][j]<prev) return; /*this is bit confusing part=> this means !(heights[i][j]>=prev)*/
        //For e.g. For Pacific Ocean : this means water will flow from top to bottom and not other way around
        visited[i][j]=true;
		/*
		question is about if rain on island can flow from island to ocean ( and not other way around i.e. question is not if ocean water can come to island )
		*/
        if(heights[i][j]>=prev) //added for clarity
        {
            for(int dir[]:dirs)
                dfs(heights, i+dir[0],j+dir[1], heights[i][j], visited);
        }
    }
}