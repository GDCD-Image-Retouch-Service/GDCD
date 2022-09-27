import numpy as np
import json


# json
def load_json(file_path):
    with open(file_path, 'r') as f:
        return json.load(f)


def load_config(config_file):
    config = load_json(config_file)
    return config


def load_samples(samples_file):
    return load_json(samples_file)


def save_json(data, target_file):
    with open(target_file, 'w') as f:
        json.dump(data, f, indent=2, sort_keys=True)


def normalize_labels(labels):
    labels_np = np.array(labels)
    return labels_np / labels_np.sum()


def calc_mean_score(score_dist):
    score_dist = normalize_labels(score_dist)
    return (score_dist*np.arange(1, 11)).sum()
