package movecontroller;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.Card;
import model.Cardstack;

public class MergeImages {
	
	private Image image;
	
	public MergeImages(Cardstack stapel){
		mergeImages(stapel);
	}

	private void mergeImages(Cardstack stapel) {
	
		if(stapel.size()==1){
			image = stapel.get(0).getFaceImage();
		}
		else{
			AnchorPane anchor = new AnchorPane();
			anchor.setLayoutX(105.0);
			anchor.setLayoutY(153.0+30*stapel.size());
			int cnt = 0;
			for(Card karteToMerge : stapel){
				ImageView imageView = new ImageView(karteToMerge.getFaceImage());
				anchor.getChildren().add(imageView);
				imageView.setLayoutY(cnt*20);
				cnt++;
			}
			image = anchor.snapshot(null, null);
		}
		
	}

	public Image getImage() {
		return image;
		
	}
}
