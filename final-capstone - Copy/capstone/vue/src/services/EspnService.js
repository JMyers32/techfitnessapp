import axios from 'axios';

const http=axios.create({
    baseURL:"https://fantasy.espn.com/apis/v3/games/ffl/seasons/2022"
});

export default{

    getTeams(leagueId){
        return http.get(`/segments/0/leagues/${leagueId}?view=mTeam`).then(response=> response.data.teams);
    },

    getTeam(leagueId, teamId){
        return http.get(`/segments/0/leagues/${leagueId}/teams/${teamId}?view=mTeam`).then(response=>response.data);
    },

    getPlayers(leagueId){
        return http.get(`/segments/0/leagues/${leagueId}?view=kona_player_info`).then(response=>response.data.players);
    },
    getPlayer(playerId){
return http.get(`/players/${playerId}`).then(response=>response.data)
    },
    
}