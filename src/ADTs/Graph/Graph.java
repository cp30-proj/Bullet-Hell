package ADTs.Graph;

public interface Graph{

   public int n(); // Number of vertices
   public int e(); // Number of edges

   public Edge first(int v); // Get first edge for vertex
   public Edge next(Edge w); // Get next edge for a vertex

   public boolean isEdge(Edge w); // True if this is an edge
   public boolean isEdge(int i, int j); // True if this is an edge

   public int v1(Edge w); // Where edge came from
   public int v2(Edge w); // Where edge goes to

   public void setEdge(int i, int j, int weight); // Set edge weight
   public void setEdge(Edge w, int weight); // Set edge weight

   public void delEdge(Edge w); // Delete edge w
   public void delEdge(int i, int j); // Delete edge (i, j)

   public int weight(int i, int j); // Return weight of edge
   public int weight(Edge w); // Return weight of edge

   public void setMark(int v, int val); // Set Mark for v
   public int getMark(int v); // Get Mark for v
} 
