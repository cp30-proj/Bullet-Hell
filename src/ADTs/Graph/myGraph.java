package ADTs.Graph;

class myGraph implements Graph { // Graph: Adjacency matrix
   private int[][] matrix; // The edge matrix
   private int numEdge; // Number of edges
   public int[] Mark; // The mark array

   public myGraph(int n){ // Constructor
       Mark = new int[n];
       matrix = new int[n][n];
       numEdge= 0;
   }

   public int n(){ 
        return Mark.length; 
   }  

   public int e(){ 
        return numEdge; 
   }  

   public Edge first(int v){ // Get the first edge for a vertex
     for (int i=0; i<Mark.length; i++)
        if (matrix[v][i] != 0)
            return new myEdge(v, i);
     return null; // No edge for this vertex
   }

   public Edge next(Edge w){ // Get next edge for a vertex
     if (w == null) 
        return null;
     for (int i=w.v2()+1; i<Mark.length; i++)
        if (matrix[w.v1()][i] != 0)
            return new myEdge(w.v1(), i);
      return null; // No next edge;
   }

   public boolean isEdge(Edge w){ // True if this is an edge
      if (w == null) return false;
      else return matrix[w.v1()][w.v2()] != 0;
   }

   public boolean isEdge(int i, int j)// True if this is an edge
   { 
      return matrix[i][j] != 0; 
   }

   public int v1(Edge w)// Where edge comes from
   { 
      return w.v1(); 
   }

   public int v2(Edge w)// Where edge goes to
   { 
      return w.v2(); 
   }

   // Set edge weight
   public void setEdge(int i, int j, int wt){
       matrix[i][j]=1;
         numEdge++;
   }
 
   // Set edge weight
   public void setEdge(Edge w, int weight){
   if (w != null)
       setEdge(w.v1(), w.v2(), weight);
   }

   public void delEdge(Edge w){ // Delete edge w
    if (w != null)
      if (matrix[w.v1()][w.v2()] != 0) {
         matrix[w.v1()][w.v2()] = 0;
         numEdge--;
       }
   }

   public void delEdge(int i, int j){ // Delete edge (i, j)
               matrix[i][j]=0;
               numEdge--;
   }

   public int weight(int i, int j){ // Return weight of edge
     if (matrix[i][j] == 0) return Integer.MAX_VALUE;
     else return matrix[i][j];
   }

   public int weight(Edge w){ // Return weight of edge
      if (w == null ) return -1; //ERROR 
         if (matrix[w.v1()][w.v2()] == 0) return Integer.MAX_VALUE;
         else 
           return matrix[w.v1()][w.v2()];
     
   }

    public void setMark(int v, int val){ 
         Mark[v] = val; 
    } // Set Mark

    public int getMark(int v){ 
          return Mark[v]; 
    } // Get Mark
}
