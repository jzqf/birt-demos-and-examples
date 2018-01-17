This report displays data from the Data Set:

	Category_FilmTitle_Actor

The query for this Data Set is:

	SELECT
		language.language_id,
		language.name AS "language",
		category.category_id, 
		category.name, 
		film.film_id,
		film.title,
		film.length,
		actor.actor_id,
		actor.first_name,
		actor.last_name
	FROM
		category 
	INNER JOIN 
		film_category ON category.category_id=film_category.category_id
	INNER JOIN 
		film ON film.film_id=film_category.film_id
	INNER JOIN 
		film_actor ON film_actor.film_id=film.film_id
	INNER JOIN
		actor ON actor.actor_id=film_actor.actor_id
	INNER JOIN 
		language ON language.language_id=film.language_id
	WHERE
		category.category_id IN (-999)
		AND film.length<=?
	ORDER BY
		category.name,
		film.title,
		actor.last_name,
		actor.first_name

Note the line:	"category.category_id IN (-999)". When the report is run, the 
string "-999" in this line is replaced by the list of Category IDs that are 
selected using the report parameter:

	rp_film_category

This report parameter is defined with the properties:

	Data type:				integer
	Allow multiple values:	True 
	Data set:				Film Categories
	Select value column:	category_id

In order to replace the string "-999" with the list of "category_id" values 
selected using this report parameter, the following "beforeOpen" script has been
specified for the "Category_FilmTitle_Actor" Data Set:

	this.queryText=this.queryText.replace("-999",params["rp_film_category"].value.join(","));

I guess that params["rp_film_category"] is exposed as a JavaScript array at 
runtime.

To see/edit this script:

	1. Click on the Data Set "Category_FilmTitle_Actor".
	2. Click the "Script" tab in the view that displays the report.

Note that there is also a "?" character in the query above. It receives the 
value that is selected for the report parameter "rp_max_length". This is the
normal way that a report parameter is used in a Data Set. Replacing the string
"-999" with using a Data Set "beforeOpen" script is done here only to document
this technique.
