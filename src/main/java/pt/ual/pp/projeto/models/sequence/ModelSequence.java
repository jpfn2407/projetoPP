package pt.ual.pp.projeto.models.sequence;

import pt.ual.pp.projeto.models.Zone;
import java.util.HashMap;


//Está classe representa a tabela 4 representada no enunciado
public class ModelSequence{
    private String modelID; //Define qual é o modelo que está sequencia representa.
    private HashMap<Integer, SequenceInfo> sequenceInfoMap = new HashMap<>(); //A chave é o numero da ordem,
                                                                            //e o objeto representa a combinação de zona e o tempo médio que ela lá deve ficar.

    public ModelSequence(String modelID) {
        this.modelID = modelID;
    }

    public void addSequenceInfo(int orderNumber, Zone zone, double average){
        this.sequenceInfoMap.put(orderNumber, new SequenceInfo(zone, average));
    }

    //Se o return for null, ele já acabou a sequencia
    public SequenceInfo getNextNotDone(){
        for(int i=1; i <= this.sequenceInfoMap.keySet().size(); i++){
            if(!this.sequenceInfoMap.get(i).isDone()){
                return this.sequenceInfoMap.get(i);
            }
        }
        return null;
    }

    public void setAsDone(int sequenceNumber){
        this.sequenceInfoMap.get(sequenceNumber).markAsDone();
    }

    public boolean isFinished() {
        for(SequenceInfo sequenceInfo : this.sequenceInfoMap.values()){
            if (!sequenceInfo.isDone()) return false;
        }
        return true;
    }

    public HashMap<Integer, SequenceInfo> getSequenceInfoMap() {
        return sequenceInfoMap;
    }

    public void debug_PrintAll(){
        for (int i = 1; i <= this.sequenceInfoMap.keySet().size(); i++){
            SequenceInfo sequenceInfo = this.sequenceInfoMap.get(i);
            System.out.println(i + " " + sequenceInfo.getZone() + " " + sequenceInfo.getAverage());
        }
    }


}
