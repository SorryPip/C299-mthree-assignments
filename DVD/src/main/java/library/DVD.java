package library;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
/**
 * DTO that holds all the DVD info
 * 
 * @author Yu
 *
 */
public class DVD {
	private String title;
	private String releaseDate;
	private String mpaaRating;
	private String directorName;
	private String studio;
    private String userRatingNote;
    
    @Override
    public String toString() {
		return String.format(
				"Title: %s,"+
				"Release Date: %s,"+
				"MPAA Rating: %s,"+
				"Director: %s,"+
				"Studio: %s,"+
				"Notes: %s,", 
				title,
				releaseDate,
				mpaaRating,
				studio,
				releaseDate,
				userRatingNote
				);	
    }
}
