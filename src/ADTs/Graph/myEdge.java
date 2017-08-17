package ADTs.Graph;

class myEdge implements Edge{
   private int vert1, vert2; // The vertex indices

   public myEdge(int vt1, int vt2)
   { 
       vert1 = vt1; 
       vert2 = vt2; 
   }

   public int v1() 
   { 
       return vert1; 
   }

   public int v2() 
   { 
       return vert2; 
   }
} 
