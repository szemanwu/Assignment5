package simpledatabase;
import java.util.ArrayList;

public class Join extends Operator{

	private ArrayList<Attribute> newAttributeList;
	private String joinPredicate;
	ArrayList<Tuple> tuples1;

	
	//Join Constructor, join fill
	public Join(Operator leftChild, Operator rightChild, String joinPredicate){
		this.leftChild = leftChild;
		this.rightChild = rightChild;
		this.joinPredicate = joinPredicate;
		newAttributeList = new ArrayList<Attribute>();
		tuples1 = new ArrayList<Tuple>();
	}

	
	/**
     * It is used to return a new tuple which is already joined by the common attribute
     * @return the new joined tuple
     */
	//The record after join with two tables
	@Override
	public Tuple next(){
		Object ID1 = null;
		Object ID2 = null;
		Tuple tuple;
		while ((tuple = leftChild.next()) != null) {
			tuples1.add(tuple);
		}
		
		Tuple tuple2 = rightChild.next();
		if (tuple2 == null)
			return null;
		else {
			for (int i = 0; i < tuple2.getAttributeList().size(); i++) {
				if (tuple2.getAttributeList().get(i).getAttributeName().equals("id")) {
					ID1 = tuple2.getAttributeList().get(i).getAttributeValue();
					break;
				}
			}
			
			for (int i = 0; i < tuples1.size(); i++) {
				for (int j = 0; j < tuples1.get(i).getAttributeList().size(); j++) {
					if (tuples1.get(i).getAttributeList().get(j).getAttributeName().equals("id")) {
						ID2 = tuples1.get(i).getAttributeList().get(j).getAttributeValue();
						if (ID1.equals(ID2))
							tuple2.getAttributeList().addAll(tuples1.get(i).getAttributeList());
					}
				}
			}
		return tuple2;
		}
	}
	
	
	/**
     * The function is used to get the attribute list of the tuple
     * @return attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		if(joinPredicate.isEmpty())
			return child.getAttributeList();
		else
			return(newAttributeList);
	}

}