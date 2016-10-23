package fc.put.edwd.dao;

/**
 * Created by marcin on 23.10.16.
 */
public class QueriesDao extends BaseDAO {
    protected  static final String SONG_RANKING = "select s.title as title, count(l.SONG_ID) as listens from " +
            "listen l join song s ON l.SONG_ID == s.ID group by l.song_id order by count(l.song_id) desc;";

/*--Ranking
    select s.title, count(l.SONG_ID) from listen l join song s ON l.SONG_ID == s.ID group by l.song_id order by count(l.song_id) desc;

--Ranking użytkowników ze względu na największą liczbę odsłuchanych unikalnych utworów
    select u.user_f_id, count(distinct(l.song_id)) as number from LISTEN l join USER u on l.USER_ID == u.ID group by u.user_f_id order by number desc;

--Artysta z największą liczbą odsłuchań
    select ARTIST_ID, COUNT(ARTIST_ID) from LISTEN group by ARTIST_ID order by COUNT(ARTIST_ID) desc LIMIT 1;

--Sumaryczna liczba odsłuchań w podziale na poszczególne miesiące
    select count(l.SONG_ID), d.MONTH from LISTEN l join DATE d ON l.DATE_ID == d.ID group by d.MONTH;
--Wszyscy użytkownicy, którzy odsłuchali wszystkie trzy najbardziej popularne piosenki zespołu Queen
    select distinct(user_id), (select user_f_id from user where id = user_id) from listen where song_id IN (
            select s.id from listen l join song s
            ON (l.SONG_ID == s.ID AND s.artist_id == (select ID from ARTIST where NAME == 'Queen'))
    group by l.song_id order by count(l.song_id) desc
    LIMIT 3
            );
*/

}
