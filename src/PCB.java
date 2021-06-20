public class PCB {
    int id;
    State state;
    int pc;
    int[] boundaries; // [10,20] 10=>min boundary and 20=> max boundary

    public PCB(int id, State state, int pc, int[] boundaries) {
        this.id = id;
        this.state = state;
        this.pc = pc;
        this.boundaries = boundaries;
    }



}
