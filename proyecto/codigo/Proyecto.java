
import java.util.*;
import java.io.*;
class Proyecto

{
    static int [] lospri=new int[4];
    static float [] losseg=new float[6];

    private static /*ArrayList<Integer>*/void elVecinoMasCercanoParaTSP(Digraph g)
    {
        int posicionDondeEstaElDeposito = 0;
        int verticeActual = posicionDondeEstaElDeposito;
    }  

    public static Digraph leer(String filename) throws IOException
    {
        FileReader fr = new FileReader(filename);
        BufferedReader lector = new BufferedReader(fr);
        int n=0,m=0, u=0, breaks =0; 
        Float r=0f, speed=0f, Tmax,Smax, st_customer, Q =0f;
        String linea1,linea; 
        linea = lector.readLine();
        String[] lineaPartida = linea.split(" ");

        for(int i=1; i<=10; i++){
            lineaPartida = linea.split(" ");
            if (i<=4){
                lospri[i-1]=Integer.parseInt(lineaPartida[2]);
            }else{
                losseg[i-5]=Float.parseFloat(lineaPartida[2]);          
            }
            linea=lector.readLine();
        }

        n=lospri[0];
        m=lospri[1];
        u=lospri[2];
        breaks=lospri[3];
        r=losseg[0];
        speed=losseg[1];
        Tmax=losseg[2];
        Smax=losseg[3];
        st_customer=losseg[4];
        Q=losseg[5];

        Digraph g = new DigraphAM(n);
        Pair<Float,Float>[] arreglo = new Pair[n];
        linea=lector.readLine();
        linea=lector.readLine();
        linea=lector.readLine();
        lineaPartida = linea.split(" ");
        for(int i=0; i<n; i++)   {
            arreglo[i] = new Pair(Float.parseFloat(lineaPartida[2]),Float.parseFloat(lineaPartida[3]));
            g.AddInfo(i,lineaPartida[1]); 
            linea=lector.readLine();
            lineaPartida = linea.split(" ");

        }
        for(int i=0; i<n; i++){
            for(int j=0;j<n;j++){
                g.addArc(i,j,((float)Math.sqrt(
                            Math.pow(arreglo[i].first-arreglo[j].first,2) +
                            Math.pow(arreglo[i].second-arreglo[j].second,2)
                        )));

            }

        }
        ////////////OJO
        linea=lector.readLine();
        linea=lector.readLine();
        linea=lector.readLine();
        lineaPartida = linea.split(" ");
        ///if(lineaPartida[0].equals("l")){
        ArrayList<String[]> losLG= new ArrayList<String[]>();
        for(int i=0; i<3;i++){
            //linea=lector.readLine();
            String [] losLpri = linea.split(" ");
            losLG.add(losLpri);
            linea=lector.readLine();

        }

        //}
        linea=lector.readLine();
        linea=lector.readLine();
        linea=lector.readLine();
        for(int i=0; i<3;i++){
            //linea=lector.readLine();
            String [] losGpri = linea.split(" ");
            losLG.add(losGpri);
            linea=lector.readLine();

        }
        return g;
    }

    public static long time1(){
        long estimatedTime=0;
        try{
            long startTime = System.currentTimeMillis();
            Digraph g= leer("tc2c320s38ct1.txt");
            estimatedTime= System.currentTimeMillis() - startTime;

        }catch(Exception e){}
        return estimatedTime;
    }

    public static long time2(){
        long estimatedTime=0;
        try{
            Digraph g= leer("tc2c320s38ct1.txt");
            long startTime = System.currentTimeMillis();
            //AgenteViajero(g);
            estimatedTime= System.currentTimeMillis() - startTime;

        }catch(Exception e){}
        return estimatedTime;
    }

    public static void main(String[] args) throws IOException
    {
        Digraph g = leer("tc2c320s24cf0.txt");
        //Ruta route= new Ruta();
        //route.AgenteViajero(g);

        // ArrayList<Integer> solucion = elVecinoMasCercanoParaTSP(g);
        System.out.println(AgenteViajero(g));
    }

    public static float AgenteViajero(Digraph g) {
        float orTime;
        int v=0;
        int de=0;//depósito
        float tiempo=0;
        float totalT=0;
        int verticesucesor=0;
        float distanciaT=0;
        float pesosucesor=0;
        boolean[] visitados = new boolean[g.size()];
        for(int j=0;j<g.size()-1;j++){
            ArrayList<Integer> sucesores= g.getSuccessors(v);
            visitados[v] = true;
            verticesucesor=-1;
            pesosucesor= Integer.MAX_VALUE;
            for(int i=0; i<sucesores.size(); i++){
                if(g.getWeight(v,sucesores.get(i))<pesosucesor && !visitados[sucesores.get(i)]){
                    verticesucesor= sucesores.get(i);
                    pesosucesor=g.getWeight(v,verticesucesor);
                }    
            }
            distanciaT=distanciaT+pesosucesor;
            tiempo=time(v,verticesucesor,g);
            totalT+=tiempo;
            v=verticesucesor;
        }
        distanciaT=distanciaT+ g.getWeight(verticesucesor,0);
        orTime=time(de,verticesucesor,g);
        return totalT+orTime;
    }

    public static float time(int prev, int next, Digraph g){
        float time;
        float dist=g.getWeight(prev, next);
        float sp=losseg[1];
        time = dist/sp;
        return time;
    }

}