module.exports = class CountVoteDto {
    answerId;
    countVote;
    constructor(answerId, count) {
        this.answerId = answerId;
        this.countVote = count
    }
}

